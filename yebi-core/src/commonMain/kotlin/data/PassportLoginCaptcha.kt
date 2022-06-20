package com.elouyi.yebi.data

@kotlinx.serialization.Serializable
public data class PassportLoginCaptcha(
    val type: String,
    val token: String,
    val geetest: Geetest,
    val tencent: Tencent
) {

    @kotlinx.serialization.Serializable
    public data class Geetest(
        val challenge: String,
        val gt: String
    )

    @kotlinx.serialization.Serializable
    public data class Tencent(
        val appid: String
    )
}
