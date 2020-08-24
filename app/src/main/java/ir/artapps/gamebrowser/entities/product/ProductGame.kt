package ir.artapps.gamebrowser.entities.product

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.UserPostInfo

data class ProductGame(

    @SerializedName("id")
    @Expose
    var id: Long = 0,

    @SerializedName("version")
    @Expose
    var version: Int? = null,

    @SerializedName("timelineId")
    @Expose
    var timelineId: Int? = null,

    @SerializedName("entityId")
    @Expose
    var entityId: Int? = null,

    @SerializedName("forwardedId")
    @Expose
    var forwardedId: Int? = null,

    @SerializedName("numOfLikes")
    @Expose
    var numOfLikes: Int? = null,

    @SerializedName("numOfDisLikes")
    @Expose
    var numOfDisLikes: Int? = null,

    @SerializedName("numOfShare")
    @Expose
    var numOfShare: Int? = null,

    @SerializedName("numOfFavorites")
    @Expose
    var numOfFavorites: Int? = null,

    @SerializedName("numOfComments")
    @Expose
    var numOfComments: Int? = null,

    @SerializedName("timestamp")
    @Expose
    var timestamp: Long? = null,

    @SerializedName("enable")
    @Expose
    var enable: Boolean? = null,

    @SerializedName("hide")
    @Expose
    var hide: Boolean? = null,

    @SerializedName("replyPostConfirmation")
    @Expose
    var replyPostConfirmation: Boolean? = null,

    @SerializedName("business")
    @Expose
    var business: Business? = null,

    @SerializedName("rate")
    @Expose
    var rate: Rate? = null,

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
    var canLike: Boolean? = null,

    @SerializedName("canRate")
    @Expose
    var canRate: Boolean? = null,

    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null,

    @SerializedName("tagTrees")
    @Expose
    var tagTrees: List<String>? = null,

    @SerializedName("attributeValues")
    @Expose
    var attributeValues: List<String>? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("categoryList")
    @Expose
    var categoryList: List<String>? = null,

    @SerializedName("unlimited")
    @Expose
    var unlimited: Boolean? = null,

    @SerializedName("availableCount")
    @Expose
    var availableCount: Int? = null,

    @SerializedName("price")
    @Expose
    var price: Int? = null,

    @SerializedName("discount")
    @Expose
    var discount: Int? = null,

    @SerializedName("allowUserInvoice")
    @Expose
    var allowUserInvoice: Boolean? = null,

    @SerializedName("allowUserPrice")
    @Expose
    var allowUserPrice: Boolean? = null,

    @SerializedName("currency")
    @Expose
    var currency: Currency? = null,

    @SerializedName("preferredTaxRate")
    @Expose
    var preferredTaxRate: Float? = null
)