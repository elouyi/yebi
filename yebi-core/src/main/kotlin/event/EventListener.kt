package com.elouyi.yebi.event

import com.elouyi.yebi.utils.Cancelable
import kotlin.coroutines.cancellation.CancellationException

public interface EventListener<out T> : Cancelable {

    override fun cancel(cause: CancellationException?)
}
