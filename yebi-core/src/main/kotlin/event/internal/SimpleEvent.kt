package com.elouyi.yebi.event.internal

import com.elouyi.yebi.event.EventConsumer
import com.elouyi.yebi.event.EventListener
import com.elouyi.yebi.event.EventPriority
import com.elouyi.yebi.event.YebiEvent
import com.elouyi.yebi.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

internal class SimpleEvent<T>(
    context: CoroutineContext
) : YebiEvent<T> {

    var listenerList: Node<EventListener<T>> = Node.Nil

    override val coroutineContext: CoroutineContext = context + SupervisorJob()

    override fun subscribe(
        context: CoroutineContext,
        priority: EventPriority,
        handler: EventConsumer<CoroutineScope, T>
    ): EventListener<T> {
        val listener = createListener(context, priority, handler)
        listenerList = listenerList.add(listener)
        return listener
    }

    override fun invoke(value: T, context: CoroutineContext): Job {
        return launch(context) {
            listenerList.forEach {
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
        listenerList = listenerList.remove(listenerList)
    }

    private fun createListener(
        context: CoroutineContext,
        priority: EventPriority,
        handler: EventConsumer<CoroutineScope, T>
    ): EventListener<T> {
        return EventHandler(
            parentJob = coroutineContext[Job],
            subscriberContext = coroutineContext + context,
            priority = priority,
            handler = handler,
            receiver = this,
            event = this
        )
    }
}