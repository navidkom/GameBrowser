package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.pod.GetMetaResponseModel
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
    suspend fun updateToken(token: String): TokenResponseModel?
    suspend fun follow(token: String?)
    suspend fun setUserMeta(userId: String, meta: String)
    suspend fun getUserMeta(userId: String): GetMetaResponseModel?
}