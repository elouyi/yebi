package com.elouyi.yebi.data

import kotlinx.serialization.Serializable

@Serializable
public data class BiliResponse<out T>(
    val code: Int,
    val message: String,
    val ttl: Int,
    val data: T?
)
