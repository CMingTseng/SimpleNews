package rooit.me.xo.model.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserInfo(
    @SerialName("agent_switch")
    val agentSwitch: String?,
    @SerialName("avatar")
    val avatar: String?,
    @SerialName("avatar_thumb")
    val avatarThumb: String?,
    @SerialName("birthday")
    val birthday: String?,
    @SerialName("change_name_cost")
    val changeNameCost: Int?,
    @SerialName("chat_level")
    val chatLevel: Int?,
    @SerialName("chat_service_url")
    val chatServiceUrl: String?,
    @SerialName("chess_url")
    val chessUrl: String?,
    @SerialName("city")
    val city: String?,
    @SerialName("coin")
    val coin: String?,
    @SerialName("consumption")
    val consumption: String?,
    @SerialName("contact_info")
    val contactInfo: String?,
    @SerialName("country_code")
    val countryCode: String?,
    @SerialName("family_switch")
    val familySwitch: String?,
    @SerialName("fans")
    val fans: Int?,
    @SerialName("follows")
    val follows: Int?,
    @SerialName("game_list_url")
    val gameListUrl: String?,
    @SerialName("game_url")
    val gameUrl: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("isBindMobile")
    val isBindMobile: Boolean?,
    @SerialName("isZeroCharge")
    val isZeroCharge: Boolean?,
    @SerialName("left_video_times")
    val leftVideoTimes: Int?,
    @SerialName("level")
    val level: String?,
    @SerialName("level_anchor")
    val level_anchor: Int?,
    @SerialName("live_ad_text")
    val liveAdText: String?,
    @SerialName("live_ad_url")
    val liveAdUrl: String?,
    @SerialName("liveShowChargeTime")
    val liveShowChargeTime: Int?,
    @SerialName("lives")
    val lives: Int?,
    @SerialName("max_video_times")
    val maxVideoTimes: Int?,
    @SerialName("notice")
    val notice: String?,
    @SerialName("notice_new")
    val noticeNew: String?,
    @SerialName("province")
    val province: String?,
    @SerialName("sex")
    val sex: String?,
    @SerialName("show_level")
    val showLevel: Int?,
    @SerialName("signature")
    val signature: String?,
    @SerialName("source")
    val source: String?,
    @SerialName("user_email")
    val userEmail: String?,
    @SerialName("user_login")
    val userLogin: String?,
    @SerialName("user_nicename")
    val nickName: String?,
    @SerialName("user_plat")
    val userPlat: String?,
    @SerialName("votes")
    val votes: String?,
    @SerialName("votestotal")
    val votestotal: String?,

    @SerialName("adlist")
    val adlist: List<Ad?>?,
    @SerialName("liang")
    val liang: Liang?,//UserVipId
    @SerialName("live_contact_cost")
    val liveContactCost: List<String?>?,
    @SerialName("livePopChargeInfo")
    val livePopChargeInfo: List<LivePopChargeInfo?>?,
    @SerialName("notices")
    val notices: List<Notice?>?,
    @SerialName("system_msg")
    val systemMsg: List<String?>?,
    @SerialName("vip")
    val vip: Vip?,
    @SerialName("list")
    val list: List<UserItemBean?>?,



//    @SerialName("app_list")
//    val appList: List<Any?>?,
)