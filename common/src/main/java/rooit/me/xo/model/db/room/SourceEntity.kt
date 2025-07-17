package rooit.me.xo.model.db.room

// 因為 Source 不是一個獨立的表，而只是 Article 的一部分，所以我們為它建立一個簡單的資料類別。
data class SourceEntity(
    val id: String?,
    val name: String?
)