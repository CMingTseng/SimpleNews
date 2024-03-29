package rooit.me.xo.model.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BankInfo(
    @SerialName("bankGate")
    val bankGate: String?,
    @SerialName("bankName")
    val bankName: String?,
    @SerialName("bankNum")
    val bankNum: String?,
    @SerialName("bankOwn")
    val bankOwn: String?
)