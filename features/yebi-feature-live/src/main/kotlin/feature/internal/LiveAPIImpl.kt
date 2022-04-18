package com.elouyi.yebi.feature.live.feature.internal

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.data.BiliResponse
import com.elouyi.yebi.feature.live.data.LiveUrl
import com.elouyi.yebi.feature.live.data.live.DanmuInfo
import com.elouyi.yebi.feature.live.data.live.LiveEvent
import com.elouyi.yebi.feature.live.feature.LiveAPI
import com.elouyi.yebi.utils.Cancelable
import com.elouyi.yebi.utils.YebiExperimental
import io.ktor.client.request.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

/*
internal class LiveAPIImpl(private val scope: YebiBot) : LiveAPI() {

    @YebiExperimental
    override fun <E : LiveEvent> subscribeLiveEvent(
        roomId: Int,
        context: CoroutineContext,
        clazz: KClass<E>,
        block: suspend E.() -> Unit
    ): Cancelable {
        throw throw throw TODO("Not yet implemented")
    }

    override suspend fun danmuInfo(id: Int): BiliResponse<DanmuInfo> {
        val url = LiveUrl.liveDanmuInfo(id)
        return scope.engine.client.get(url) {
            with(scope.engine) { doSomething() }
        }
    }
}
*/
