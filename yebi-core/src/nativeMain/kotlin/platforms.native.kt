package com.elouyi.yebi

import io.ktor.client.*
import io.ktor.client.engine.curl.*
import io.ktor.util.date.*

public actual val platformUtil: PlatformUtil get() = NativePlatform

public object NativePlatform : PlatformUtil {
    override fun newHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
        return HttpClient(Curl) {
            block()
        }
    }

    override fun currentTimeMillis(): Long = getTimeMillis()

    override val isNative: Boolean
        get() = true

    override val isNativeWindows: Boolean
        get() = isWindows

    override val isNativeMacOS: Boolean
        get() = isMacOS

    override val isNativeLinux: Boolean
        get() = isLinux
}

public expect val NativePlatform.isWindows: Boolean

public expect val NativePlatform.isMacOS: Boolean

public expect val NativePlatform.isLinux: Boolean
