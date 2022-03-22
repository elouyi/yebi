package com.elouyi.yebi.engine.web.internal

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.engine.web.YWEngineConfig
import com.elouyi.yebi.engine.YebiEngineBase
import io.ktor.client.request.*
import kotlin.coroutines.CoroutineContext

public class YebiWebEngine(
    override val config: YWEngineConfig
) : YebiEngineBase("WebEngine") {

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")

    override val uid: Long
        get() = TODO()

    override fun install(bot: YebiBot) {
        TODO("Not yet implemented")
    }

    override var isLogin: Boolean = false

    override fun HttpRequestBuilder.doSomething() {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}