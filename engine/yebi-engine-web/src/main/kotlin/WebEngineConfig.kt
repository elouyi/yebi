package com.elouyi.yebi.engine.web

import com.elouyi.yebi.engine.YebiEngineConfig
import com.elouyi.yebi.utils.YebiInternalAPI

public class WebEngineConfig : YebiEngineConfig {

    internal var cookie = UserCookie.Empty

    internal var account = "" to ""

    public fun withUserCookie(cookie: UserCookie) {
        this.cookie = cookie
    }

    @OptIn(YebiInternalAPI::class)
    public fun withUserCookie(cookieStr: String) {
        this.cookie = cookieStr.getUserCookie()
    }

    override fun withAccount(account: String, pass: String) {
        this.account = account to pass
    }
}