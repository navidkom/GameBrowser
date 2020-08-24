package ir.artapps.gamebrowser.entities.product

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ir.artapps.gamebrowser.entities.Rate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Business (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,

    @SerializedName("numOfProducts")
    @Expose
    var numOfProducts: Int? = null,

    @SerializedName("rate")
    @Expose
    var rate: Rate? = null,

    @SerializedName("sheba")
    @Expose
    var sheba: String? = null
): Parcelable