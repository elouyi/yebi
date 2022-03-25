package com.elouyi.yebi.feature

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.data.BiliResponse
import com.elouyi.yebi.data.live.DanmuInfo
import com.elouyi.yebi.data.live.LiveEvent
import com.elouyi.yebi.feature.internal.LiveAPIImpl
import com.elouyi.yebi.utils.Cancelable
import com.elouyi.yebi.utils.YebiExperimental
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass

public val YebiBot.liveApi: LiveAPI
    get() = attributes[Live.key].liveAPI

public abstract class LiveAPI {

    /**
     * 订阅直播间信息
     */
    @YebiExperimental
    public abstract fun <E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext = EmptyCoroutineContext,
        clazz: KClass<E>,
        block: suspend E.() -> Unit
    ): Cancelable

    @YebiExperimental
    public inline fun <reified E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext = EmptyCoroutineContext,
        noinline block: suspend E.() -> Unit
    ): Cancelable = subscribeLiveEvent(roomId, context, E::class, block)

    public abstract suspend fun danmuInfo(id: Int): BiliResponse<DanmuInfo>

    public companion object {

        @YebiExperimental
        private val instances = mutableMapOf<YebiBot, LiveAPI>()

        @OptIn(YebiExperimental::class)
        internal operator fun get(scope: YebiBot): LiveAPI {
            return instances[scope] ?: LiveAPIImpl(scope).also { instances[scope] = it }
        }
    }
}
