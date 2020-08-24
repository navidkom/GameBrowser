package ir.artapps.gamebrowser.entities.product

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency (
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("code")
    @Expose
    var code: String? = null
): Parcelable