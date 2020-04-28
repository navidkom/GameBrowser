package ir.artapps.gamebrowser.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("ClientMessageId")
    @Expose
    var clientMessageId: Any? = null,
    @SerializedName("Count")
    @Expose
    var count: Int? = null,
    @SerializedName("HasError")
    @Expose
    var hasError: Boolean? = null,
    @SerializedName("ErrorMessage")
    @Expose
    var errorMessage: Any? = null,
    @SerializedName("ErrorCode")
    @Expose
    var errorCode: Int? = null,
    @SerializedName("Result")
    @Expose
    var game: List<Game>? = null
)