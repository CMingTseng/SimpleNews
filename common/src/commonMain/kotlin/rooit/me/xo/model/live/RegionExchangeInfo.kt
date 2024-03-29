package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RegionExchangeInfo(
    @SerialName("exchange_rate")
    val exchangeRate: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("region")
    val region: String?,
    @SerialName("region_curreny")
    val regionCurreny: String?,
    @SerialName("region_curreny_char")
    val regionCurrenyChar: String?,
    @SerialName("region_curreny_icon")
    val regionCurrenyIcon: String?,
    @SerialName("region_icon")
    val regionIcon: String?
)