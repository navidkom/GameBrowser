package ir.artapps.gamebrowser.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.remote.PodRemoteDataSource
import ir.artapps.gamebrowser.ui.util.preferences.SharedPref
import ir.artapps.gamebrowser.ui.util.preferences.SharedPrefKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class PodRepositoryImpl(
    val context: Context,
    private val remote: PodRemoteDataSource
) : PodRepository {

    override var profileLiveData: MutableLiveData<UserProfile?> = MutableLiveData()
    override var token: String? = null
    override var profile: UserProfile? = null

    override suspend fun getUserProfile(): UserProfile? {
        if( !SharedPref.DEFAULT.getString(SharedPrefKeys.TOKEN, "").isNullOrBlank() ){
            token = SharedPref.DEFAULT.getString(SharedPrefKeys.TOKEN, "")
            PodRepository.token = token
            profile = SharedPref.DEFAULT.getParcelable(SharedPrefKeys.PROFILE, null, UserProfile::class.java)
            PodRepository.profile = profile
            profileLiveData.postValue(profile)
            return profile
        }

        profileLiveData.postValue(null)
        return null
    }

    override suspend fun getUserProfile(tk: String): UserProfile? {

        withContext(Dispatchers.IO) {
            token = remote.getUserToken(tk)?.access_token
            PodRepository.token = token
            SharedPref.DEFAULT.storeString(SharedPrefKeys.TOKEN, token)
        }

        val result = remote.getUserProfile(token!!)?.result
        profile = result
        SharedPref.DEFAULT.storeParcelable(SharedPrefKeys.PROFILE, profile)
        PodRepository.profile = profile
        profileLiveData.postValue(result)
        return result
    }

    override fun signOut() {
        token = ""
        PodRepository.token = ""
        profile = null
        PodRepository.profile = null
        SharedPref.DEFAULT.clear()
        profileLiveData.postValue(null)
    }
}
