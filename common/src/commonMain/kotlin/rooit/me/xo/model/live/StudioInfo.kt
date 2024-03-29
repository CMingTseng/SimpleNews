package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import rooit.me.xo.api.live.SELF

@Serializable
public data class StudioInfo(
    @SerialName("anyway")
    val anyway: String?,
    @SerialName("avatar")
    val avatar: String?,
    @SerialName("avatar_thumb")
    val avatarThumb: String?,
    @SerialName("city")
    val city: String?,
    @SerialName("consumption")
    val consumption: String?,
    @SerialName("game")
    val game: String?,
    @SerialName("game_action")
    var gameAction:Int = 0, //正在进行的游戏的标识
    @SerialName("goodnum")
    val goodnum: String? = null, //主播的靓号
    @SerialName("have_red_envelope")
    val haveRedEnvelope: Int?,
    @SerialName("isvideo")
    val isvideo: String?,
    @SerialName("level")
    val level: Int?,
    @SerialName("level_anchor")
    val levelAnchor: Int?,
    @SerialName("live_ip")
    val liveIp: String?,
    @SerialName("live_languages")
    val liveLanguages: String?,
    @SerialName("live_plat")
    val livePlat: String?,
    @SerialName("live_pos")
    val livePos: String?,
    @SerialName("live_regions")
    val liveRegions: List<String?>?,
    @SerialName("lottery_name")
    val lotteryName: String?,
    @SerialName("lottery_type")
    val lotteryType: String?,
    @SerialName("nums")
    val nums: String?,
    @SerialName("pull")
    val pull: String?,
    @SerialName("red_left_count")
    val redLeftCount: String?,
    @SerialName("sex")
    val sex: String?,
    @SerialName("source")
    val source: String?,
    @SerialName("starttime")
    val starttime: String?,
    @SerialName("stream")
    val stream: String?,
    @SerialName("supportVibrator")
    val supportVibrator: Boolean?,
    @SerialName("thumb")
    val thumb: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("type_val")
    val typeVal: String?,
    @SerialName("uid")
    val uid: String?,
    @SerialName("user_nicename")
    val userNicename: String?,
    @SerialName("votestotal")
    val votestotal: String?
)

public typealias LiveBean =StudioInfo