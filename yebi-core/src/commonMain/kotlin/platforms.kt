package com.elouyi.yebi

import io.ktor.client.*

public expect val platformUtil: PlatformUtil

public interface PlatformUtil {

    public fun newHttpClient(): HttpClient

    public fun currentTimeMillis(): Long

    public val isJvm: Boolean

    public val jsJs: Boolean

    public val isNative: Boolean

    public val isNativeWindows: Boolean

    public val isNativeLinux: Boolean

    public val isNativeMacOS: Boolean
}

