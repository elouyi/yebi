package com.elouyi.yebi.feature

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.data.live.LiveEvent
import com.elouyi.yebi.utils.Cancelable
import com.elouyi.yebi.utils.YebiExperimental
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public val YebiBot.liveApi: LiveAPI
    get() = attributes[Live.key].liveAPI

public interface LiveAPI {

    /**
     * 订阅直播间信息
     */
    @YebiExperimental
    public fun <E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend E.() -> Unit
    ): Cancelable

    public companion object {

        private var instance: LiveAPI? = null

        internal operator fun get(scope: YebiBot): LiveAPI {
            instance?.let { return it }
            return TODO()
        }
    }
}
