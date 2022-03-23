package com.elouyi.yebi.engine.web.internal

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.engine.YebiEngineBase
import com.elouyi.yebi.engine.web.WebEngineConfig
import io.ktor.client.request.*
import kotlin.coroutines.CoroutineContext

public class YebiWebEngine(
    override val config: WebEngineConfig
) : YebiEngineBase("YebiEngine-Web") {

    override val coroutineContext: CoroutineContext
        get() = super.coroutineContext

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