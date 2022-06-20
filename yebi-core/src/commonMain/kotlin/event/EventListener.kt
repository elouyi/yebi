package com.elouyi.yebi.event

import com.elouyi.yebi.util.Cancelable
import kotlinx.coroutines.CompletableJob
import kotlin.coroutines.cancellation.CancellationException

public interface EventListener<T> : Cancelable, CompletableJob {

    public val priority: EventPriority get() = EventPriority

    public suspend fun onEvent(value: T)

    override fun cancel(cause: kotlinx.coroutines.CancellationException?)
}
