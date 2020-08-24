package ir.artapps.gamebrowser.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.artapps.gamebrowser.SingleLiveEvent
import ir.artapps.gamebrowser.entities.EventBus
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.remote.PodRemoteDataSource
import ir.artapps.gamebrowser.ui.util.preferences.SharedPref
import ir.artapps.gamebrowser.ui.util.preferences.SharedPrefKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class PodRepositoryImpl(
    val context: Context,
    private val remote: PodRemoteDataSource
) : PodRepository {

    override var token: String? = null
    override var refreshToken: String? = null
    override var profile: UserProfile? = null

    override val profileLiveData = MutableLiveData<UserProfile?>()

    override suspend fun updateToken() {

        try {
            if(refreshToken.isNullOrEmpty()) {
                return
            }

            val result = remote.updateToken(refreshToken!!)
            if (result?.access_token != null) {
                token = result?.access_token
                refreshToken = result?.refresh_token
                SharedPref.DEFAULT.storeString(SharedPrefKeys.TOKEN, token)
                SharedPref.DEFAULT.storeString(SharedPrefKeys.REFRESH_TOKEN, refreshToken)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override  fun getUserProfile(): LiveData<UserProfile?> {
        runBlocking(Dispatchers.IO) {
            if (!SharedPref.DEFAULT.getString(SharedPrefKeys.REFRESH_TOKEN, "").isNullOrBlank()) {
                token = SharedPref.DEFAULT.getString(SharedPrefKeys.TOKEN, "")
                refreshToken = SharedPref.DEFAULT.getString(SharedPrefKeys.REFRESH_TOKEN, "")
                withContext(Dispatchers.IO) {
                    updateToken()
                }
                profile = SharedPref.DEFAULT.getParcelable(
                    SharedPrefKeys.PROFILE,
                    null,
                    UserProfile::class.java
                )
                profileLiveData.postValue(profile)
//            return profile
            } else {
                profileLiveData.postValue(null)
            }
        }
        return profileLiveData
    }

    override  fun getUserProfile(tk: String): LiveData<UserProfile?> {
        runBlocking(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                val result = remote.getUserToken(tk)
                token = result?.access_token
                refreshToken = result?.refresh_token
                SharedPref.DEFAULT.storeString(SharedPrefKeys.TOKEN, token)
                SharedPref.DEFAULT.storeString(SharedPrefKeys.REFRESH_TOKEN, refreshToken)
                true
            }

            var result: UserProfile? = null
            if (!token.isNullOrEmpty()) {
                result = remote.getUserProfile(token!!)?.result
                profile = result
                SharedPref.DEFAULT.storeParcelable(SharedPrefKeys.PROFILE, profile)
                profileLiveData.postValue(result)
                remote.follow(token!!)
            }
        }
        return profileLiveData
    }


    override fun signOut() {
        token = ""
        refreshToken = ""
        profile = null
        SharedPref.DEFAULT.clear()
        profileLiveData.postValue(null)
    }
}
