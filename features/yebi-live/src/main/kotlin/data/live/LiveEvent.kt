package com.elouyi.yebi.data.live

import com.elouyi.yebi.feature.internal.bodyHexString
import com.elouyi.yebi.feature.internal.headHexString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public sealed interface LiveEvent {

    public sealed interface CMD : LiveEvent {
        public val cmd: String
    }
}

/**
 * 认证包
 */
public class CertPack internal constructor(
    public val data: ByteArray
) : LiveEvent {
    public val message: String

    init {
        val body = ByteArray(data.size - 0x10) {
            data[it + 0x10]
        }
        message = String(body)
    }

    override fun toString(): String {
        return "认证包: $message"
    }
}

public class Heartbeat internal constructor(
    public val popularity: UInt
) : LiveEvent {
    override fun toString(): String {
        return "心跳回复-人气值:$popularity"
    }
}

/**
 * 未知信息
 */
public open class UnknownEvent internal constructor(
    public val data: ByteArray
) : LiveEvent {
    override fun toString(): String {
        return buildString {
            append("Unknown live room event:\n")
            append("head: ")
            append(headHexString(data)).append("\n")
            append("body: ")
            append(bodyHexString(data)).append("\n")
            append(String(data))
        }
    }
}

public class UnknownCMD internal constructor(
    override val cmd: String,
    data: ByteArray
) : UnknownEvent(data), LiveEvent.CMD

public data class RoomRealTimeMessageUpdate(
    override val cmd: String, // ROOM_REAL_TIME_MESSAGE_UPDATE
    val data: RoomRealTimeMessageUpdate.Data
) : LiveEvent, LiveEvent.CMD {
    @Serializable
    public data class Data(
        @SerialName("room_id")
        val roomId: Int,
        val fans: Int,
        @SerialName("red_notice")
        val redNotice: Int,
        @SerialName("fans_club")
        val fansClub: Int
    )
}
