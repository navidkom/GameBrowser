package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel

/**
 * Created by navid
 */
class PodRemoteDataSourceImpl: PodRemoteDataSource {

    val CONTENT_TYPE = "application/x-www-form-urlencoded"
    val GRANT_TYPE = "authorization_code"
    val REDIRECT_URL = "http://www.kidzy.ir/redirect_app"
    val CLIENT_ID = "18060069b4bf248759168bc24000d57c7"
    val CLIENT_SECRET = "b3884890"
    val TOKEN_ISSUER = "1"

    val service by lazy {
        ServiceGenerator.createPodAccountService()
    }

    val sandboxService by lazy {
        ServiceGenerator.createPodSandboxService()
    }

    override suspend fun getUserProfile(token: String): GetProfileResponseModel {
        val token = service.getUserToken(CONTENT_TYPE, GRANT_TYPE, token, REDIRECT_URL, CLIENT_ID, CLIENT_SECRET)
        return sandboxService.getUserProfile(token.access_token, TOKEN_ISSUER, CLIENT_ID, CLIENT_SECRET)
    }

}