package ir.artapps.gamebrowser.repo

import android.content.Context
import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.GetProfileResult
import ir.artapps.gamebrowser.remote.PodRemoteDataSource

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class PodRepositoryImpl(
    val context: Context,
    private val remote: PodRemoteDataSource
) : PodRepository {

    override suspend fun getUserProfile(token: String): GetProfileResponseModel {
        return remote.getUserProfile(token)
    }

}