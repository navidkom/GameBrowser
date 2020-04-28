package ir.artapps.gamebrowser.entities

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ir.artapps.gamebrowser.entities.Game.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize


@Entity(tableName = TABLE_NAME, indices = [Index(value = ["id"], unique = true)])

@Parcelize
data class Game (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_")
    @Transient
    var id_: Long = 0,
    @SerializedName("ApkSize")
    @Expose
    var apkSize: Int? = null,
    @SerializedName("AllowHoldingLeague")
    @Expose
    var allowHoldingLeague: Boolean? = null,
    @SerializedName("PublishType")
    @Expose
    var publishType: Int? = null,
    @SerializedName("HasSdk")
    @Expose
    var hasSdk: Boolean? = null,
    @SerializedName("HasLeague")
    @Expose
    var hasLeague: Boolean? = null,
    @SerializedName("Lobby")
    @Expose
    var lobby: Lobby? = null,
    @SerializedName("Suggestion")
    @Expose
    var suggestion: Int? = null,
    @SerializedName("ChannelThread")
    @Expose
    var channelThread: Int? = null,
    @SerializedName("SupporterID")
    @Expose
    var supporterID: Int? = null,
    @SerializedName("MobileVersionCode")
    @Expose
    var mobileVersionCode: Int? = null,
    @SerializedName("WebVersion")
    @Expose
    var webVersion: String? = null,
    @SerializedName("MobileVersion")
    @Expose
    var mobileVersion: String? = null,
    @SerializedName("DownloadLink")
    @Expose
    var downloadLink: String? = null,
    @SerializedName("Platform")
    @Expose
    var platform: Int? = null,
    @SerializedName("DefaultLeague")
    @Expose
    var defaultLeague: Int? = null,
    @SerializedName("Score")
    @Expose
    var score: Int? = null,
    @SerializedName("GamePlayDesc")
    @Expose
    var gamePlayDesc: String? = null,
    @SerializedName("PackageName")
    @Expose
    var packageName: String? = null,
    @SerializedName("Creator")
    @Expose
    var creator: Int? = null,
    @SerializedName("PublishedDate")
    @Expose
    var publishedDate: String? = null,
    @SerializedName("GamePlayerNumbersType")
    @Expose
    var gamePlayerNumbersType: Int? = null,
    @SerializedName("GameStatus")
    @Expose
    var gameStatus: Int? = null,
    @SerializedName("PhysicalUrl")
    @Expose
    var physicalUrl: String? = null,
    @SerializedName("Changelog")
    @Expose
    var changelog: String? = null,
    @SerializedName("Infrustructure")
    @Expose
    var infrustructure: Int? = null,
    @SerializedName("Esrb")
    @Expose
    var esrb: Int? = null,
    @SerializedName("Banner")
    @Expose
    var banner: String? = null,
    @SerializedName("preview")
    @Expose
    var preview: String? = null,
    @SerializedName("Name")
    @Expose
    var name: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("availableCount")
    @Expose
    var availableCount: Float? = null,
    @SerializedName("price")
    @Expose
    var price: Int? = null,
    @SerializedName("discount")
    @Expose
    var discount: Int? = null,
    @SerializedName("rate")
    @Expose
    var rate: Rate? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("timelineId")
    @Expose
    var timelineId: Int? = null,
    @SerializedName("entityId")
    @Expose
    var entityId: Int? = null,
    @SerializedName("numOfLikes")
    @Expose
    var numOfLikes: Int? = null,
    @SerializedName("numOfFavorites")
    @Expose
    var numOfFavorites: Int? = null,
    @SerializedName("numOfComments")
    @Expose
    var numOfComments: Int? = null,
    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null,
    @SerializedName("enable")
    @Expose
    var enable: Boolean? = null,
    @SerializedName("hide")
    @Expose
    var hide: Boolean? = null,
    @SerializedName("business")
    @Expose
    var business: Business? = null,
    @SerializedName("userPostInfo")
    @Expose
    var userPostInfo: UserPostInfo? = null,
    @SerializedName("metadata")
    @Expose
    var metadata: String? = null,
    @SerializedName("latitude")
    @Expose
    var latitude: Float? = null,
    @SerializedName("longitude")
    @Expose
    var longitude: Float? = null,
    @SerializedName("canComment")
    @Expose
    var canComment: Boolean? = null,
    @SerializedName("canLike")
    @Expose
    var canLike: Boolean? = null
) :Parcelable {
    companion object {
        @Ignore
        @Transient
        const val TABLE_NAME = "response"
    }
}