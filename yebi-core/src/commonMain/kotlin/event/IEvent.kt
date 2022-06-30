package com.elouyi.yebi.event

import com.elouyi.yebi.util.YebiExperimentalAPI
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public typealias EventConsumer<R, T> = suspend R.(T) -> Unit


/**
 * 事件系统, 由 [subscribe] 订阅事件, [invoke] 触发事件, todo
 */
@YebiExperimentalAPI
public interface IEvent<T, out R : Any?, out Listener : EventListener<T>> {

    /**
     * 订阅事件
     * @param context 协程 context,触发时会加入所在协程
     * @param handler
     */
    public fun subscribe(
        context: CoroutineContext = EmptyCoroutineContext,
        priority: EventPriority = EventPriority,
        handler: EventConsumer<R, T>
    ): Listener

    /**
     * 触发事件
     */
    public operator fun invoke(
        value: T,
        context: CoroutineContext = EmptyCoroutineContext
    ): Job

    public operator fun plus(
        handler: EventConsumer<R, T>
    ): Listener = subscribe(handler = handler)

    public operator fun plusAssign(
        handler: EventConsumer<R, T>
    ): Unit = Unit.also { this + handler }

    public fun onCancel(listener: @UnsafeVariance Listener, cause: CancellationException? = null)

    public fun onComplete(listener: @UnsafeVariance Listener)
}

private fun <T, R> (suspend (T) -> Unit).wrap(): EventConsumer<R, T> = { invoke(it) }

@OptIn(YebiExperimentalAPI::class)
public fun <T, Listener : EventListener<T>> IEvent<T, *, Listener>.subscribe(
    handler: suspend (T) -> Unit,
    context: CoroutineContext = EmptyCoroutineContext,
    priority: EventPriority = EventPriority
): Listener = subscribe(context, priority, handler.wrap())
