package com.elouyi.yebi.feature.live.data

import com.elouyi.yebi.data.BiliUrl

public interface LiveUrl : BiliUrl {

    public fun liveDanmuInfo(id: Int): String {
        return "https://api.live.bilibili.com/xlive/web-room/v1/index/getDanmuInfo?id=$id"
    }

    public fun liveRoomPlayInfo(id: Int): String {
        return "https://api.live.bilibili.com/xlive/web-room/v1/index/getRoomPlayInfo?room_id=$id"
    }

    public companion object : LiveUrl
}