package com.elouyi.yebi.event.internal

import com.elouyi.yebi.event.EventConsumer
import com.elouyi.yebi.event.EventListener
import com.elouyi.yebi.event.EventPriority
import com.elouyi.yebi.event.YebiEvent
import com.elouyi.yebi.util.*
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal class SimpleEvent<T>(
    context: CoroutineContext
) : YebiEvent<T> {

    private val listenerList: AtomicRef<Node<EventListener<T>>> = atomic(Node.Nil)

    override val coroutineContext: CoroutineContext = context + SupervisorJob()

    override fun subscribe(
        context: CoroutineContext,
        priority: EventPriority,
        handler: EventConsumer<CoroutineScope, T>
    ): EventListener<T> {
        val listener = createListener(context, priority, handler)
        listenerList.addListener(listener)
        return listener
    }

    override fun invoke(value: T, context: CoroutineContext): Job {
        return launch(context) {
            listenerList.value.forEach {
                try {
                    launch { it.onEvent(value) }
                } catch (e: Throwable) {
                    if (e is CancellationException) throw e
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCancel(listener: EventListener<T>, cause: CancellationException?) {
        listenerList.getAndUpdate {
            it.remove(listener)
        }
    }

    override fun onComplete(listener: EventListener<T>) {
        listenerList.getAndUpdate {
            it.remove(listener)
        }
    }

    private fun createListener(
        context: CoroutineContext,
        priority: EventPriority,
        handler: EventConsumer<CoroutineScope, T>
    ): EventListener<T> {
        val receiver = CoroutineScope(coroutineContext + Job(coroutineContext[Job]) + context)
        return SimpleListener(
            parentJob = coroutineContext[Job],
            subscribeContext = coroutineContext + context,
            priority = priority,
            handler = handler,
            receiver = { receiver },
            event = this
        )
    }

    companion object {

        inline fun <T> AtomicRef<Node<EventListener<T>>>.addListener(
            listener: EventListener<T>
        ): Node<EventListener<T>> = getAndUpdate { node ->
            if (node.isEmpty) return@getAndUpdate Node.Cons(listener, Node.Nil)
            val priority = listener.priority
            buildNode {
                var added = false
                node.forEach {
                    if (it.priority <= priority && !added) {
                        append(listener)
                        append(it)
                        added = true
                    } else append(it)
                }
                if (!added) append(listener)
            }.reverse()
        }
    }
}
