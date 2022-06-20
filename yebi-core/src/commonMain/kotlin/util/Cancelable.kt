package com.elouyi.yebi.util

import kotlinx.coroutines.CancellationException

public interface Cancelable {
    public fun cancel(cause: CancellationException? = null)
}
