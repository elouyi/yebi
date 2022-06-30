package com.elouyi.yebi

import io.ktor.client.*

public expect val platformUtil: PlatformUtil

/**
 * 平台工具类
 */
public interface PlatformUtil {

    public fun newHttpClient(block: HttpClientConfig<*>.() -> Unit = {}): HttpClient

    public fun currentTimeMillis(): Long

    public val isJvm: Boolean get() = false

    public val jsJs: Boolean get() = false

    public val isNative: Boolean get() = false

    public val isNativeWindows: Boolean get() = false

    public val isNativeLinux: Boolean get() = false

    public val isNativeMacOS: Boolean get() = false
}

