package com.elouyi.yebi.feature.live.feature.internal

import com.elouyi.yebi.feature.live.data.live.*
import com.elouyi.yebi.feature.live.data.live.LiveCMD
import com.elouyi.yebi.utils.toHexString
import io.ktor.utils.io.core.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal fun liveDanmuCertPack(
    roomId: Int,
    uid: Int = 0,
    protoVer: Int = 3,
    platform: String = "web",
    type: Int = 2,
    key: String = ""
): ByteArray {
    val data = buildJsonObject {
        put("uid", uid)
        put("roomid", roomId)
        put("protover", protoVer)
        put("platform", platform)
        put("type", type)
        put("key", key)
    }.toString().toByteArray()
    val size = 16 + data.size
    return buildPacket {
        writeInt(size) // 封包总大小
        writeShort(0x10) // 头部大小
        writeShort(1) // 协议版本
        writeInt(7) // 类型
        writeInt(1)
        writePacket(ByteReadPacket(data))
    }.readBytes()
}

internal fun parseLiveEvent(data: ByteArray): LiveEvent {
    return when(data[0x0b].toInt()) {
        3 -> parseHeartbeat(data)
        8 -> CertPack(data)
        else -> UnknownEvent(data)
    }
}

/**
 * @param data 单个心跳回复信息流
 * @return 人气
 */
@OptIn(ExperimentalUnsignedTypes::class)
@Deprecated("")
internal fun parseHeartbeat(data: ByteArray): Heartbeat {
    // require(data[0x03] <= 0x14) { "not a single heartbeat" }
    require(data[0x0b].toInt() == 3) { "not a heartbeat" }
    val p = ByteArray(4) { data[it + 0x10] }
    return Heartbeat(ByteReadPacket(p).readUInt())
}

internal fun parseSingleNormalPack(data: ByteArray): LiveEvent.CMD {
    val body = String(ByteArray(data.size - 0x10) {
        data[it + 0x10]
    })
    return when(val cmd = body.substringAfter(""""cmd":"""").substringBefore("""","""")) {
        LiveCMD.roomRealTimeMessageUpdate -> Json.decodeFromString<RoomRealTimeMessageUpdate>(body)
        else -> UnknownCMD(cmd, data)
    }
}

/**
 * 头部格式
 * @property totalSize 封包总大小
 * @property headSize 头部大小
 * @property pv 协议版本
 *
 * 0普通包正文不使用压缩
 *
 * 1心跳及认证包正文不使用压缩
 *
 * 2普通包正文使用zlib压缩
 *
 * 3普通包正文使用brotli压缩,解压为一个带头部的协议0普通包
 * @property opCode 操作码
 *
 * 2 心跳包
 *
 * 3 心跳包回复
 *
 * 5 普通包（cmd）
 *
 * 7 认证包
 *
 * 8 认证包回复
 */
internal class LiveEventHead(
    val totalSize: Int, // uint offset 0, size 4
    val headSize: Int = 0x10, // offset4 size 2
    val pv: Int, // offset 6, size 2
    val opCode: Int, // offset 8, size 4
    val sequence: Int // offset 12, size 4
)

internal fun ByteReadPacket.readHead(): LiveEventHead {
    return LiveEventHead(
        totalSize = this.readInt(),
        headSize = this.readShort().toInt(),
        pv = this.readShort().toInt(),
        opCode = this.readInt(),
        sequence = this.readInt()
    )
}

internal fun headHexString(data: ByteArray): String {
    return buildString {
        append("[")
        for (i in 0 until 0x10) {
            append(data[i].toHexString())
            when(i) {
                0x03,0x05,0x07,0x0b -> append(" || ")
                else -> if (i != 0x0f) append(",")
            }
        }
        append("]")
    }
}

internal fun bodyHexString(data: ByteArray): String {
    return buildString {
        append("[")
        for (i in 0x10 until data.size) {
            append(data[i].toHexString())
            if (i != data.size - 1) append(",")
        }
        append("]")
    }
}
