package ir.artapps.gamebrowser.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lobby (
    @SerializedName("ID")
    @Expose
    var iD: Int? = null,
    @SerializedName("Name")
    @Expose
    var name: String? = null,
    @SerializedName("Timestamp")
    @Expose
    var timestamp: String? = null,
    @SerializedName("Ind")
    @Expose
    var ind: Int? = null
) : Parcelable