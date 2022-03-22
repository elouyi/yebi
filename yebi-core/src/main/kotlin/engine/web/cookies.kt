package com.elouyi.yebi.engine.web

import com.elouyi.yebi.utils.YebiInternalAPI
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

public sealed interface CookieProperty {
    public object SESSDATA : CookieProperty
    public object DedeUserID : CookieProperty
    public object DedeUserID__ckMd5 : CookieProperty
    public object bili_jct : CookieProperty
}

private val CookieProperty.propertyName: String
    get() = javaClass.simpleName

@YebiInternalAPI
internal operator fun String.get(property: CookieProperty): String {
    if (!this.contains(property.propertyName)) {
        System.err.println("Cookie ${property.propertyName} not found!")
        return ""
    }
    return this.substringAfter("${property.propertyName}=")
        .substringBefore(";")
}

@YebiInternalAPI
internal fun String.getUserCookie(): UserCookie {
    return UserCookie(
        sessData = this[CookieProperty.SESSDATA],
        dedeUserId = this[CookieProperty.DedeUserID].toLong(),
        dedeUserIDCkMd5 = this[CookieProperty.DedeUserID__ckMd5],
        bili_jct = this[CookieProperty.bili_jct]
    )
}

@OptIn(YebiInternalAPI::class)
public operator fun Headers.get(property: CookieProperty): String {
    return toString()[property]
}

@Serializable
public data class UserCookie(
    val sessData: String,
    val dedeUserId: Long,
    val dedeUserIDCkMd5: String,
    val bili_jct: String,
) {
    val isEmpty: Boolean
        get() = sessData.isEmpty()
                && dedeUserId == 0L
                && dedeUserIDCkMd5.isEmpty()
                && bili_jct.isEmpty()

    public companion object {
        public val Empty: UserCookie = UserCookie("", 0, "", "")
    }
}

public fun HttpRequestBuilder.withUserCookie(cookie: UserCookie) {
    cookie("SESSDATA",cookie.sessData)
    cookie("DedeUserID",cookie.dedeUserId.toString())
    cookie("DedeUserID__ckMd5",cookie.dedeUserIDCkMd5)
    cookie("bili_jct",cookie.bili_jct)
}

public fun main() {
    val c = "_uuid=C8E14B49-E497-C33B-A5C6-1091461034CAFE98300infoc; buvid3=3CBDF1D8-D8EC-4A97-8CF3-2C7F4BC4177C167639infoc; b_nut=1637760898; video_page_version=v_old_home; rpdid=|(JYYRlYR~Yu0J'uYJ~~Y|mkm; i-wanna-go-back=-1; CURRENT_BLACKGAP=0; LIVE_BUVID=AUTO3216384519439372; blackside_state=0; fingerprint3=c9640a46a3fe6a3c8b0b8d92fd96d929; fingerprint_s=d4243ad47f2f5cd83e92fd91a323c3d9; buvid_fp_plain=undefined; sid=lkhsipbk; DedeUserID=84301837; DedeUserID__ckMd5=70c09f12a2807587; bili_jct=fbf688b67fa881df35b0daee34cf7ba7; b_ut=5; buvid4=41E1D160-6183-AEB8-4726-4347B85206C498376-022012418-Z+jkrkK4bspKpHAffJmSdQ%3D%3D; CURRENT_QUALITY=112; nostalgia_conf=-1; fingerprint=05da3daf5605966fb5c8f1db5a8e799a; buvid_fp=05da3daf5605966fb5c8f1db5a8e799a; PVID=1; bp_video_offset_84301837=640054344564080600; CURRENT_FNVAL=80; innersign=0; b_lsid=6B7DAB72_17FB1EB28FD"
    val cookie = c.getUserCookie()
    println(cookie)
}