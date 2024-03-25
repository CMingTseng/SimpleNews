package rooit.me.xo.model.db.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import org.mongodb.kbson.ObjectId

open class SourceRealm(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),
    @SerialName("id")
    var id: String? = null,
    @SerialName("name")
    var name: String? = null,
): RealmObject {
    constructor() : this(_id = ObjectId())
}