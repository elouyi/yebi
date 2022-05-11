package com.elouyi.yebi.feature.user.internal

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.data.BiliResponse
import com.elouyi.yebi.feature.user.UserAPI
import com.elouyi.yebi.feature.user.data.AccountInfo
import com.elouyi.yebi.feature.user.data.UserUrl
import com.elouyi.yebi.utils.newClient
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

internal class UserAPIImpl(override val bot: YebiBot) : UserAPI() {

    override val client: HttpClient = newClient {
        BrowserUserAgent()
    }

    override suspend fun userAccountInfo(mid: Long): BiliResponse<AccountInfo> {
        return client.get(UserUrl.accountInfo(mid)) {
            bot.engine.apply { doSomething() }
        }
    }
}