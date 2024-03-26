package rooit.me.xo.model.db.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import org.mongodb.kbson.ObjectId

public open class SourceRealm(
    @PrimaryKey
    public var _id: ObjectId = ObjectId(),
    @SerialName("id")
    public var id: String? = null,
    @SerialName("name")
    public var name: String? = null,
) : RealmObject {
    public constructor() : this(_id = ObjectId())
}