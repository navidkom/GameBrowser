package ir.artapps.gamebrowser.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayPodResponseLogin (
    @SerializedName("Image")
    var image: String? = null,

    @SerializedName("CustomerID")
    var customerID : Int? = null,

    @SerializedName("Name")
    var name: String?  = null,

    @SerializedName("UserID")
    var userID: String? = null,

    @SerializedName("Token")
    var token: String? = null,

    @SerializedName("ProfileImage")
    var profileImage: String? = null,

    @SerializedName("CellphoneNumber")
    var cellphoneNumber: String? = null,

    @SerializedName("Username")
    var username: String? = null
) : Parcelable
