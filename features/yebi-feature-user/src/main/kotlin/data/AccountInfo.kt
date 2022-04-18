package com.elouyi.yebi.feature.user.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
public data class AccountInfo(
    val mid: Int,
    val name: String,
    val sex: String, // 男，女，保密
    val face: String,
    @SerialName("face_nft")
    val faceNft: Int,
    val sign: String,
    val rank: Int,
    val level: Int,
    @SerialName("jointime")
    val joinTime: Int,
    val moral: Int,
    val silence: Int,
    val coins: Int,
    @SerialName("fans_badge")
    val fansBadge: Boolean,
    @SerialName("fans_medal")
    val fansMedal: FansMedal,
    val official: Official,
    val vip: VIP,
    val pendant: Pendant,
    val nameplate: Nameplate,
    @SerialName("user_honour_info")
    val userHonourInfo: UserHonourInfo,
    @SerialName("is_followed")
    val isFollowed: Boolean,
    @SerialName("top_photo")
    val topPhoto: String,
    val theme: Theme,
    @SerialName("sys_notice")
    val sysNotice: SysNotice,
    @SerialName("live_room")
    val liveRoom: LiveRoom,
    val birthday: String, // MM-DD
    val school: School,
    val profession: Profession,
    val tags: Tag?,
    val series: Series,
    @SerialName("is_senior_member")
    val isSeniorMember: Int
) {
    @Serializable
    public data class Official(
        val role: Int,
        val title: String?,
        val desc: String?,
        val type: Int
    )

    @Serializable
    public data class VIP(
        val type: Int,
        val status: Int,
        @SerialName("due_date")
        val dueDate: Long,
        @SerialName("theme_type")
        val themeType: Int,
        val label: Label,
        @SerialName("avatar_subscript")
        val avatarSubscript: Int,
        @SerialName("nickname_color")
        val nicknameColor: String
    ) {
        @Serializable
        public data class Label(
            val path: String,
            val text: String,
            @SerialName("label_theme")
            val labelTheme: String
        )
    }

    @Serializable
    public data class Pendant(
        val pid: Int,
        val name: String,
        val image: String,
        val expire: Int
    )

    @Serializable
    public data class Nameplate(
        val nid: Int,
        val name: String,
        val image: String,
        @SerialName("image_small")
        val imageSmall: String,
        val level: String,
        val condition: String
    )

    @Serializable
    public data class SysNotice(
        val id: Int?,
        val content: String?,
        val url: String?,
        @SerialName("notice_type")
        val noticeType: Int?,
        val icon: String?,
        @SerialName("text_color")
        val textColor: String?,
        @SerialName("bg_color")
        val bgColor: String?
    )

    @Serializable
    public data class LiveRoom(
        val roomStatus: Int,
        val liveStatus: Int,
        val url: String,
        val title: String,
        val cover: String,
        @SerialName("watched_show")
        val watchedShow: WatchedShow,
        @SerialName("roomid")
        val roomId: Int,
        val roundStatus: Int,
        @SerialName("broadcast_type")
        val broadcastType: Int
    ) {
        @Serializable
        public data class WatchedShow(
            val switch: Boolean,
            val num: Int,
            @SerialName("text_small")
            val textSmall: String,
            @SerialName("text_large")
            val textLarge: String,
            val icon: String,
            @SerialName("icon_location")
            val iconLocation: String,
            @SerialName("icon_web")
            val iconWeb: String
        )
    }

    @Serializable
    public data class School(
        val name: String
    )

    @Serializable
    public data class Profession(
        val name: String
    )

    @Serializable
    public data class Series(
        @SerialName("user_upgrade_status")
        val userUpgradeStatus: Int,
        @SerialName("show_upgrade_window")
        val showUpgradeWindow: Boolean
    )

    @Serializable
    public data class FansMedal(
        val show: Boolean,
        val wear: Boolean,
        val medal: Medal
    ) {
        @Serializable
        public data class Medal(
            val uid: Int,
            @SerialName("target_id")
            val targetId: Int,
            @SerialName("medal_id")
            val medalId: Int,
            val level: Int,
            @SerialName("medal_name")
            val medalName: String,
            @SerialName("medal_color")
            val medalColor: Int,
            val intimacy: Int,
            @SerialName("next_intimacy")
            val nextIntimacy: Int,
            @SerialName("day_limit")
            val dayLimit: Int,
            @SerialName("medal_color_start")
            val medalColorStart: Int,
            @SerialName("medal_color_end")
            val medalColorEnd: Int,
            @SerialName("medal_color_border")
            val medalColorBorder: Int,
            @SerialName("is_lighted")
            val isLighted: Int,
            // @SerialName("is_status")
            // val isStatus: Int?,
            @SerialName("wearing_status")
            val wearingStatus: Int,
            val score: Int
        )
    }

    @Serializable
    public data class UserHonourInfo(
        val mid: Int,
        val colour: String?, // todo
        val tags: List<String> // todo
    )

    @Serializable
    public class Theme

    @Serializable
    public class Tag
}