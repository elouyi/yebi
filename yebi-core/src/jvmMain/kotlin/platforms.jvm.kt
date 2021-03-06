package com.elouyi.yebi

import io.ktor.client.*
import io.ktor.client.engine.cio.*

public actual val platformUtil: PlatformUtil
    get() = JVMPlatform

private object JVMPlatform : PlatformUtil {
    override fun newHttpClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
        return HttpClient(CIO) {
            block()
        }
    }

    override fun currentTimeMillis(): Long = System.currentTimeMillis()

    override val isJvm: Boolean get() = true
    override val jsJs: Boolean get() = false
    override val isNative: Boolean get() = false
    override val isNativeWindows: Boolean get() = false
    override val isNativeLinux: Boolean get() = false
    override val isNativeMacOS: Boolean get() = false
}
