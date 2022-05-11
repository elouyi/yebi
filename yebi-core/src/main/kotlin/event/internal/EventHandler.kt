package com.elouyi.yebi.event.internal

import com.elouyi.yebi.event.EventConsumer
import com.elouyi.yebi.event.EventListener
import com.elouyi.yebi.event.EventPriority
import com.elouyi.yebi.event.YebiEvent
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

internal class EventHandler<T, R>(
    parentJob: Job?,
    subscriberContext: CoroutineContext,
    override val priority: EventPriority,
    val handler: EventConsumer<R, T>,
    val receiver: R,
    val event: YebiEvent<T>,
    private val jobDelegate: CompletableJob = SupervisorJob(parentJob)
) : EventListener<T>, CompletableJob by jobDelegate {

    private val subscribeContext: CoroutineContext = subscriberContext + this

    private var cancelCause: CancellationException? = null

    override suspend fun onEvent(value: T) {
        if (!isActive) {
            // listener is canceled
            throw cancelCause ?: CancellationException()
        }
        try {
            withContext(subscribeContext) {
                handler(receiver, value)
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

}
