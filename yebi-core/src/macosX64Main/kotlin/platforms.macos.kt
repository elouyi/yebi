package com.elouyi.yebi

public actual val NativePlatform.isWindows: Boolean get() = false

public actual val NativePlatform.isMacOS: Boolean get() = true

public actual val NativePlatform.isLinux: Boolean get() = false
