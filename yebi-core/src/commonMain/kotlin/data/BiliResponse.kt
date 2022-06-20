package com.elouyi.yebi.data

/**
 * json 回复对象
 * @property code 返回值
 * @property message 返回信息
 * @property ttl 1 尚不明确
 * @property data 信息本体
 */
@kotlinx.serialization.Serializable
public data class BiliResponse<out Data>(
    val code: Int,
    val message: String,
    val ttl: Int,
    val data: Data?
) where Data : Any
