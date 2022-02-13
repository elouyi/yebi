package com.elouyi.yebi.utils

import kotlin.coroutines.cancellation.CancellationException

public interface Cancelable {
    public fun cancel(cause: CancellationException? = null)
}
