package ir.artapps.gamebrowser.remote
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.TokenResponseModel
import ir.artapps.gamebrowser.entities.product.FavoriteResponse
import ir.artapps.gamebrowser.entities.product.ProductGame
import ir.artapps.gamebrowser.entities.product.GeneralResponseModel
import ir.artapps.gamebrowser.entities.product.Products
import retrofit2.http.*

/**
 * Created by navid
 */
interface PodApi {

    @POST("oauth2/token")
    suspend fun getUserToken(
        @Header("Content-Type") ContentType: String?,
        @Query("grant_type") grantType: String?,
        @Query("code") code: String?,
        @Query("redirect_uri") redirectUri: String?,
        @Query("client_id") clientId: String?,
        @Query("client_secret") clientSecret: String?
    ): TokenResponseModel

    @POST("oauth2/token")
    suspend fun updateToken(
        @Header("Content-Type") ContentType: String?,
        @Query("grant_type") grantType: String?,
        @Query("refresh_token") refreshToken: String?,
        @Query("client_id") clientId: String?,
        @Query("client_secret") clientSecret: String?
    ): TokenResponseModel

    @POST("/nzh/getUserProfile")
    suspend fun getUserProfile(
        @Header("_token_") token: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("client_id") clientId: String?,
        @Query("client_secret") clientSecret: String?
    ): GetProfileResponseModel

    @GET("/nzh/productList")
    suspend fun getProductList(
        @Header("accept") accept: String?,
        @Query("businessId") businessId: String?,
        @Query("size") size: Int?,
        @Query("offset") offset: Int?,
        @Query("scProductId") productId: String?
    ): GeneralResponseModel<List<ProductGame>>

    @GET("/nzh/searchProduct")
    suspend fun searchProduct(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Header("accept") accept: String?,
        @Query("businessId") businessId: String?,
        @Query("size") size: Int?,
        @Query("offset") offset: Int?,
        @Query("id") productId: String?
    ): GeneralResponseModel<Products>

    @GET("/nzh/getFavorites")
    suspend fun getFavorites(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Header("accept") accept: String?,
        @Query("size") size: Int?,
        @Query("offset") offset: Int?
    ): GeneralResponseModel<List<FavoriteResponse>>

    @GET("/nzh/like")
    suspend fun likeProduct(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("postId") postId: String?,
        @Query("dislike") dislike: Boolean?
    ): GeneralResponseModel<Boolean>

    @GET("/nzh/dislikePost")
    suspend fun dislikeProduct(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("postId") postId: String?,
        @Query("dislike") dislike: Boolean?
    ): GeneralResponseModel<Boolean>

    @GET("/nzh/favorite")
    suspend fun favoriteProduct(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("postId") postId: String?,
        @Query("disfavorite") favorite: Boolean?
    ): GeneralResponseModel<Boolean>

    @GET("/nzh/rate")
    suspend fun rateProduct(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("postId") postId: String?,
        @Query("rate") rate: Float?
    ): GeneralResponseModel<Rate>

    @POST("/nzh/follow")
    suspend fun follow(
        @Header("_token_") businessToken: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("businessId") businessId: String?,
        @Query("follow") follow: Boolean?
    ): GeneralResponseModel<Boolean>
}