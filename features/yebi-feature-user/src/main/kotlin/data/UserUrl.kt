package com.elouyi.yebi.feature.user.data

import com.elouyi.yebi.data.BiliUrl

public interface UserUrl : BiliUrl {

    public fun accountInfo(mid: Long): String = "https://api.bilibili.com/x/space/acc/info?mid=$mid"

    public companion object : UserUrl
}