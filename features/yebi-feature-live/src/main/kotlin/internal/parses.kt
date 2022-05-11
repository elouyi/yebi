package com.elouyi.yebi.feature.live.internal

import com.elouyi.yebi.feature.live.data.live.*
import com.elouyi.yebi.feature.live.data.live.LiveCMD
import io.ktor.utils.io.core.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal interface LiveEventParser {

    /**
     * 解析单个
     */
    fun parse(data: ByteArray): LiveEvent
}

internal class Parser(
    private val parser: LiveEventParser
) {

    fun parseAll(data: ByteArray): List<LiveEvent> {
        return listOf(parser.parse(data))
        val pack = ByteReadPacket(data)
        val head = pack.readHead()
        if (pack.remaining.toInt() == head.totalSize - head.headSize) {
            return listOf(parser.parse(data))
        }

        return listOf(UnknownEvent(data))
    }
}

internal object LiveEventParserImpl : LiveEventParser {

    override fun parse(data: ByteArray): LiveEvent {
        return when(data[0x0b].toInt()) {
            0x03 -> parseHeartBeat(data)
            0x05 -> {
                parseSingleCMD(data)
            }
            0x08 -> CertPack(data)
            else -> UnknownEvent(data)
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun parseHeartBeat(data: ByteArray): Heartbeat {
        require(data[0x0b].toInt() == 3) { "not a heartbeat" }
        val p = ByteArray(4) { data[it + 0x10] }
        return Heartbeat(ByteReadPacket(p).readUInt())
    }

    private fun parseSingleCMD(data: ByteArray): LiveEvent.CMD {
        val pack = ByteReadPacket(data)
        val head = pack.readHead()
        if (pack.remaining.toInt() != head.totalSize - head.headSize) {
            println("###")
        }
        require(head.opCode == 5) { "Not a CMD event" }
        val bodyString = String(pack.readBytes())
        val cmd = bodyString
            .substringAfter(""""cmd":"""")
            .substringBefore("""","""")
        return when(cmd) {
            LiveCMD.roomRealTimeMessageUpdate -> {
                Json.decodeFromString<RoomRealTimeMessageUpdate>(bodyString)
            }
            else -> UnknownCMD(cmd, data)
        }
    }

    /**
     * @param data 单个心跳回复信息流
     * @return 人气
     */
    @OptIn(ExperimentalUnsignedTypes::class)
    internal fun parseHeartbeat(data: ByteArray): Heartbeat {
        // require(data[0x03] <= 0x14) { "not a single heartbeat" }
        require(data[0x0b].toInt() == 3) { "not a heartbeat" }
        val p = ByteArray(4) { data[it + 0x10] }
        return Heartbeat(ByteReadPacket(p).readUInt())
    }
}
