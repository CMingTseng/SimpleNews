package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LiveStudioInfo(
    @SerialName("list")
    val list: List<StudioInfo>?,
    @SerialName("live_current_region")
    val liveCurrentRegion: RegionInfo?,
    @SerialName("live_support_regions")
    val liveSupportRegions: List<RegionInfo?>?
)