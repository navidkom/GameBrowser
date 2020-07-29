package ir.artapps.gamebrowser.repo

import android.content.Context
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.remote.PodRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class PodRepositoryImpl(
    val context: Context,
    private val remote: PodRemoteDataSource
) : PodRepository {

    override suspend fun getUserProfile(token: String) : UserProfile? {

            withContext(Dispatchers.IO) {
                App.token = remote.getUserToken(token)?.access_token
            }

            val result = remote.getUserProfile(App.token!!)?.result
            App.profile.value = result
            return result
    }
}