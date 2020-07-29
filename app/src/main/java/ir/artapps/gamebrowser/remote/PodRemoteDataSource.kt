package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.TokenResponseModel

/**
 * Created by navid
 */
interface PodRemoteDataSource {
    suspend fun getUserProfile(
        token: String
    ): GetProfileResponseModel?

    suspend fun getUserToken(token: String): TokenResponseModel?
}