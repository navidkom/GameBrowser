package ir.artapps.gamebrowser.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserPostInfo (
    @SerializedName("liked")
    @Expose
    var liked: Boolean? = null,
    @SerializedName("favorite")
    @Expose
    var favorite: Boolean? = null,
    @SerializedName("postId")
    @Expose
    var postId: Int? = null
) : Parcelable