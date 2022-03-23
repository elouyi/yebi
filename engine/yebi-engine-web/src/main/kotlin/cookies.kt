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
