package com.elouyi.yebi.event.internal

import com.elouyi.yebi.event.EventConsumer
import com.elouyi.yebi.event.EventListener
import com.elouyi.yebi.event.EventPriority
import com.elouyi.yebi.event.YebiEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal class SimpleListener<T, R>(
    parentJob: Job?,
    subscribeContext: CoroutineContext,
    override val priority: EventPriority,
    val handler: EventConsumer<R, T>,
    val receiver: () -> R,
    val event: YebiEvent<T>,
    private val jobDelegate: CompletableJob = SupervisorJob(parentJob)
) : EventListener<T>, CompletableJob by jobDelegate{

    private val subscribeContext: CoroutineContext = subscribeContext + this

    private var cancelCause: CancellationException? = null

    override suspend fun onEvent(value: T) {
        if (!isActive) {
            throw cancelCause ?: CancellationException("Listener is canceled")
        }
        try {
            withContext(subscribeContext) {
                handler(receiver(), value)
            }
        } catch (e: Throwable) {
            if (e is CancellationException) throw e
            e.printStackTrace()
        }
    }

    override fun cancel(cause: CancellationException?) {
        event.onCancel(this, cause)
        this.cancelCause = cause
        jobDelegate.cancel(cause)
    }

    override fun complete(): Boolean {
        return jobDelegate.complete().also {
            event.onComplete(this)
        }
    }
}