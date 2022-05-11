package com.elouyi.yebi.event

import com.elouyi.yebi.event.internal.SimpleEvent
import com.elouyi.yebi.utils.YebiExperimental
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(YebiExperimental::class)
public interface YebiEvent<T> : IEvent<T, CoroutineScope, EventListener<T>>, CoroutineScope {

    public companion object {
        public fun <T> create(context: CoroutineContext = EmptyCoroutineContext): YebiEvent<T> = SimpleEvent(context)
    }
}
