package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel

/**
 * Created by navid
 */
interface PodRemoteDataSource {
    suspend fun getUserProfile(
        token: String
    ): GetProfileResponseModel
}