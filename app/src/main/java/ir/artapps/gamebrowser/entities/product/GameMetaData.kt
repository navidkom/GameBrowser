package ir.artapps.gamebrowser.entities.product

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Navid Komijani
 * on 17,August,2020
 */

@Parcelize
data class GameMetaData(
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("isPlayPod")
    @Expose
    var isPlayPod: Boolean = false,

    @SerializedName("instruction")
    @Expose
    var instruction: String? = null,

    @SerializedName("parentalInfo")
    @Expose
    var parentalInfo: String? = null,

    @SerializedName("image")
    @Expose
    var image: List<String>? = null,

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null,

    @SerializedName("video")
    @Expose
    var video: String? = null,

    @SerializedName("ageRange")
    @Expose
    var ageRange: Int? = null,

    @SerializedName("relatedGames")
    @Expose
    var relatedGames: List<Int>? = null,

    @SerializedName("types")
    @Expose
    var types: List<String>? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<String>? = null

) : Parcelable