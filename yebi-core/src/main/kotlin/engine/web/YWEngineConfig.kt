package com.elouyi.yebi.engine.web

import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.utils.YebiInternalAPI

public class YWEngineConfig : YebiEngineConfig {

    public var cookie: UserCookie = UserCookie.Empty
        internal set

    @OptIn(YebiInternalAPI::class)
    public fun withCookie(cookieStr: String) {
        this.cookie = cookieStr.getUserCookie()
    }

    public fun withCookie(cookie: UserCookie) {
        this.cookie = cookie
    }
}