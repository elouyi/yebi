package com.elouyi.yebi

import io.ktor.client.*
import io.ktor.client.engine.js.*
import kotlin.js.Date

public actual val platformUtil: PlatformUtil get() = JsPlatform

private object JsPlatform : PlatformUtil {
    override fun newHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
        return HttpClient(Js) {
            block()
        }
    }

    override fun currentTimeMillis(): Long = Date.now().toLong()

    override val jsJs: Boolean get() = true

}
