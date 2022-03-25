package com.elouyi.yebi.feature.live.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DanmuInfo(
    val group: String,
    @SerialName("business_id")
    val businessId: Int,
    @SerialName("refresh_row_factor")
    val refreshRowFactor: Double,
    @SerialName("refresh_rate")
    val refreshRate: Int,
    @SerialName("max_delay")
    val maxDelay: Int,
    val token: String,
    @SerialName("host_list")
    val hostList: List<Host>
) {

    @Serializable
    public data class Host(
        val host: String,
        val port: Int,
        @SerialName("wss_port")
        val wssPort: Int,
        @SerialName("ws_port")
        val wsPort: Int
    )
}
