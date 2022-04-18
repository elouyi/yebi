package com.elouyi.yebi.engine.web.internal

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.engine.YebiEngineBase
import com.elouyi.yebi.engine.web.WebEngineConfig
import com.elouyi.yebi.engine.web.withUserCookie
import com.elouyi.yebi.utils.YebiInternalAPI
import com.elouyi.yebi.utils.newClient
import io.ktor.client.*
import io.ktor.client.request.*
import kotlin.coroutines.CoroutineContext

internal class YebiWebEngine(
    override val config: WebEngineConfig
) : YebiEngineBase("YebiEngine-Web") {

    override val client: HttpClient = newClient {  }

    override val coroutineContext: CoroutineContext
        get() = super.coroutineContext

    override val uid: Long
        get() = config.cookie.dedeUserId

    @OptIn(YebiInternalAPI::class)
    override fun install(bot: YebiBot) {

    }

    override var isLogin: Boolean = false

    override fun HttpRequestBuilder.doSomething() {
        with(config.cookie) {
            if (!isEmpty) withUserCookie(this)
        }
    }

    override fun close() {

    }
}