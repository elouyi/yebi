package com.elouyi.yebi.feature.live

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.data.BiliResponse
import com.elouyi.yebi.feature.live.data.live.DanmuInfo
import com.elouyi.yebi.feature.live.data.live.LiveEvent
import com.elouyi.yebi.utils.Cancelable
import com.elouyi.yebi.utils.YebiExperimental
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass

public val YebiBot.liveApi: LiveAPI
    get() = attributes[Live].liveAPI

public class LiveAPI internal constructor(
    private val scope: YebiBot
){

    /**
     * 订阅直播间信息
     */
    @YebiExperimental
    public fun <E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext = EmptyCoroutineContext,
        clazz: KClass<E>,
        block: suspend E.() -> Unit
    ): Cancelable = TODO()

    @YebiExperimental
    public inline fun <reified E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext = EmptyCoroutineContext,
        noinline block: suspend E.() -> Unit
    ): Cancelable = subscribeLiveEvent(roomId, context, E::class, block)

    public suspend fun danmuInfo(id: Int): BiliResponse<DanmuInfo> = TODO()

}
