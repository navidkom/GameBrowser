package ir.artapps.gamebrowser.entities.product

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeneralResponseModel<T> (
    @SerializedName("hasError")
    @Expose
    var hasError: Boolean? = null,

    @SerializedName("referenceNumber")
    @Expose
    var referenceNumber: String? = null,

    @SerializedName("errorCode")
    @Expose
    var errorCode: Int? = null,

    @SerializedName("count")
    @Expose
    var count: Int? = null,

    @SerializedName("result")
    @Expose
    var product: T? = null
)