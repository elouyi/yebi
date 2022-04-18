package com.elouyi.yebi.feature.user

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.data.BiliResponse
import com.elouyi.yebi.feature.YebiAPIBase
import com.elouyi.yebi.feature.user.data.AccountInfo

public val YebiBot.userAPI: UserAPI
    get() = attributes[UserFeature.key].userAPI

public abstract class UserAPI: YebiAPIBase {

    public abstract suspend fun userAccountInfo(mid: Long): BiliResponse<AccountInfo>

    public suspend fun userAccountInfo(): BiliResponse<AccountInfo> = userAccountInfo(bot.engine.uid)
}
