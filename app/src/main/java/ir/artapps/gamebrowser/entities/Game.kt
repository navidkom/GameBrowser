package ir.artapps.gamebrowser.entities

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ir.artapps.gamebrowser.entities.Game.Companion.TABLE_NAME
import ir.artapps.gamebrowser.entities.product.GameMetaData
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_NAME, indices = [Index(value = ["id"], unique = true)])


@Parcelize
data class Game(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Transient
    var id: Long = 0,

    var version: Int? = null,

    var entityId: Int? = null,

    var numOfLikes: Int? = null,

    var numOfDisLikes: Int? = null,

    var numOfShare: Int? = null,

    var numOfFavorites: Int? = null,

    var numOfComments: Int? = null,

    var timestamp: Long? = null,

    var enable: Boolean? = null,

    var hide: Boolean? = null,

    var rate: Rate? = null,

    var metadata: GameMetaData? = null,

    var canComment: Boolean? = null,

    var canLike: Boolean? = null,

    var canRate: Boolean? = null,

    var tags: List<String>? = null,

    var name: String? = null,

    var description: String? = null,

    var userPostInfo: UserPostInfo? = null

) : Parcelable{
    companion object {
        @Ignore
        @Transient
        const val TABLE_NAME = "games"
    }
}