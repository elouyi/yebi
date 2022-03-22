package com.elouyi.yebi.feature

import com.elouyi.yebi.Yebi
import com.elouyi.yebi.data.live.LiveEvent
import com.elouyi.yebi.utils.Cancelable
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public val Yebi.liveApi: LiveAPI
    get() = attributes[Live.key].liveAPI

public interface LiveAPI {

    /**
     * 订阅直播间信息
     */
    public fun <E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend E.() -> Unit
    ): Cancelable

    public companion object {

        private var instance: LiveAPI? = null

        internal operator fun get(scope: Yebi): LiveAPI {
            instance?.let { return it }
            return TODO()
        }
    }
}