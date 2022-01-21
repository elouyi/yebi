package com.elouyi.yebi.event

import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 事件系统, 由 [subscribe] 订阅事件, [invoke] 触发事件
 */
public interface IEvent<T, out R : Any?, out J : Any?> {

    /**
     * 订阅事件
     * @param context 协程 context,触发时会加入所在协程
     * @param handler
     */
    public fun subscribe(
        context: CoroutineContext = EmptyCoroutineContext,
        handler: EventHandler<R, T>
    ): J

    /**
     * 触发事件
     */
    public operator fun invoke(
        value: T,
        context: CoroutineContext = EmptyCoroutineContext
    ): Job
}
