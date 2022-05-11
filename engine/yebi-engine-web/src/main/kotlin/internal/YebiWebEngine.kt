package com.elouyi.yebi.engine.web.internal

import com.elouyi.yebi.YebiBot
import com.elouyi.yebi.engine.YebiEngineBase
import com.elouyi.yebi.engine.web.WebEngineConfig
import com.elouyi.yebi.engine.web.withUserCookie
import com.elouyi.yebi.engine.web.withUserCookieString
import com.elouyi.yebi.utils.YebiInternalAPI
import com.elouyi.yebi.utils.newClient
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

internal class YebiWebEngine(
    override val config: WebEngineConfig
) : YebiEngineBase("YebiEngine-Web") {

    override val client: HttpClient = newClient {  }

    override val coroutineContext: CoroutineContext = super.coroutineContext

    override val uid: Long
        get() = config.cookie.dedeUserId

    @OptIn(YebiInternalAPI::class)
    override fun install(bot: YebiBot) {

    }

    override var isLogin: Boolean = false

    override fun HttpRequestBuilder.doSomething() {
        withUserCookieString(
            "_uuid=C8E14B49-E497-C33B-A5C6-1091461034CAFE98300infoc; buvid3=3CBDF1D8-D8EC-4A97-8CF3-2C7F4BC4177C167639infoc; video_page_version=v_old_home; rpdid=|(JYYRlYR~Yu0J'uYJ~~Y|mkm; i-wanna-go-back=-1; CURRENT_BLACKGAP=0; LIVE_BUVID=AUTO3216384519439372; blackside_state=0; fingerprint3=c9640a46a3fe6a3c8b0b8d92fd96d929; fingerprint_s=d4243ad47f2f5cd83e92fd91a323c3d9; buvid_fp_plain=undefined; sid=lkhsipbk; buvid4=41E1D160-6183-AEB8-4726-4347B85206C498376-022012418-Z+jkrkK4bspKpHAffJmSdQ%3D%3D; CURRENT_QUALITY=112; nostalgia_conf=-1; fingerprint=05da3daf5605966fb5c8f1db5a8e799a; buvid_fp=05da3daf5605966fb5c8f1db5a8e799a; DedeUserID=84301837; DedeUserID__ckMd5=70c09f12a2807587; SESSDATA=c740dc0a%2C1663511294%2C9e139*31; bili_jct=2f9c174822758112577421313e15aecf; b_ut=5; innersign=1; bp_video_offset_84301837=650427304921530400; bp_article_offset_84301837=650424826725400600; PVID=1; CURRENT_FNVAL=80; b_lsid=893A2696_1803D171022"
        )
        with(config.cookie) {
            if (!isEmpty) withUserCookie(this)
        }
    }

    override fun close() {
        coroutineContext[Job]!!.cancel()
    }
}