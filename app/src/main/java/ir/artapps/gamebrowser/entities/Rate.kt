package ir.artapps.gamebrowser.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rate (
    @SerializedName("myRate")
    @Expose
    var myRate: Float? = null,
    @SerializedName("rate")
    @Expose
    var rate: Float? = null,
    @SerializedName("rateCount")
    @Expose
    var rateCount: Int? = null
) : Parcelable