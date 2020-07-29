package ir.artapps.gamebrowser.remote
import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.TokenResponseModel
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

    @POST("/nzh/getUserProfile")
    suspend fun getUserProfile(
        @Header("_token_") token: String?,
        @Header("_token_issuer_") tokenIssuer: String?,
        @Query("client_id") clientId: String?,
        @Query("client_secret") clientSecret: String?
    ): GetProfileResponseModel
}