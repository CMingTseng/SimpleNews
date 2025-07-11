package rooit.me.xo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Source(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)