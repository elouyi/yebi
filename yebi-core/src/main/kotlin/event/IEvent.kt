package com.elouyi.yebi.event

import com.elouyi.yebi.utils.YebiExperimental
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 事件系统, 由 [subscribe] 订阅事件, [invoke] 触发事件, todo
 */
@YebiExperimental
public interface IEvent<T, out R : Any?, out Listener : EventListener<T>> {

    /**
     * 订阅事件
     * @param context 协程 context,触发时会加入所在协程
     * @param handler
     */
    public fun subscribe(
        context: CoroutineContext = EmptyCoroutineContext,
        priority: EventPriority = EventPriority.NORMAL,
        handler: EventHandler<R, T>
    ): Listener

    /**
     * 触发事件
     */
    public operator fun invoke(
        value: T,
        context: CoroutineContext = EmptyCoroutineContext
    ): Job

    public operator fun plus(
        handler: EventHandler<R, T>
    ): Listener = subscribe(handler = handler)

    public operator fun plusAssign(
        handler: EventHandler<R, T>
    ): Unit = Unit.also { this + handler }

}
