package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Content(
    @SerialName("amountRange")
    val amountRange: List<String>?,
    @SerialName("channelID")
    val channelID: String?,
    @SerialName("curreny_unit")
    val currenyUnit: String?,
    @SerialName("discount_each_base")
    val discountEachBase: String?,
    @SerialName("discount_each_give")
    val discountEachGive: String?,
    @SerialName("discount_maxamount")
    val discountMaxamount: String?,
    @SerialName("discount_rate")
    val discountRate: String?,
    @SerialName("discount_type")
    val discountType: String?,
    @SerialName("exchange_rate")
    val exchangeRate: String?,

    @SerialName("openCustom")
    val openCustom: String?,
    @SerialName("quick_amount")
    val quickAmount: List<QuickAmount>?,
    @SerialName("showPayBtn")
    val showPayBtn: Int?,
    @SerialName("subTitle")
    val subTitle: String?,
    @SerialName("tip")
    val tip: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("viewString")
    val viewString: String?,
    @SerialName("list")
    val list: List<BankInfo>?,
)