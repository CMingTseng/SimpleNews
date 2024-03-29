package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BankAccountInfo(
    @SerialName("account")
    val account: String?,
    @SerialName("account_bank")
    val accountBank: String?,
    @SerialName("address")
    val address: String?,
    @SerialName("addtime")
    val addtime: String?,
    @SerialName("deleted")
    val deleted: String?,
    @SerialName("gate")
    val gate: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("mark_status")
    val markStatus: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("plat")
    val plat: String?,
    @SerialName("region_id")
    val regionId: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("uid")
    val uid: String?
)