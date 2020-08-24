package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.TokenResponseModel
import java.lang.Exception

/**
 * Created by navid
 */
class PodRemoteDataSourceImpl: PodRemoteDataSource {

    val CONTENT_TYPE = "application/x-www-form-urlencoded"
    val GRANT_TYPE_AUTH = "authorization_code"
    val GRANT_TYPE_REFRESH = "refresh_token"
    val REDIRECT_URL = "http://www.kidzy.ir/redirect_app"
    val CLIENT_ID = "18060069b4bf248759168bc24000d57c7"
    val CLIENT_SECRET = "b3884890"
    val TOKEN_ISSUER = "1"
    val BUSINESS_ID = "18463"

    val service by lazy {
        ServiceGenerator.createPodAccountService()
    }

    val sandboxService by lazy {
        ServiceGenerator.createPodSandboxService()
    }

    override suspend fun getUserToken(token: String): TokenResponseModel? {
        return try {
            service.getUserToken(
                CONTENT_TYPE,
                GRANT_TYPE_AUTH,
                token,
                REDIRECT_URL,
                CLIENT_ID,
                CLIENT_SECRET
            )
        } catch (e:Exception) {
            null
        }
    }

    override suspend fun updateToken(accessToken: String): TokenResponseModel? {
        return try {
            service.updateToken(
                CONTENT_TYPE,
                GRANT_TYPE_REFRESH,
                accessToken,
                CLIENT_ID,
                CLIENT_SECRET
            )
        } catch (e:Exception) {
            null
        }
    }

    override suspend fun getUserProfile(accessToken: String): GetProfileResponseModel? {
        return try {
            sandboxService.getUserProfile(
                accessToken,
                TOKEN_ISSUER,
                CLIENT_ID,
                CLIENT_SECRET
            )
        } catch (e:Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun follow(token: String?) {
        sandboxService.follow(token , TOKEN_ISSUER, BUSINESS_ID, true)
    }

}