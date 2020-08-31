package ir.artapps.gamebrowser.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import ir.artapps.gamebrowser.entities.PlayPodResponseLogin
import ir.artapps.gamebrowser.entities.pod.ClientMetadata
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.remote.PodRemoteDataSource
import ir.artapps.gamebrowser.ui.util.preferences.SharedPref
import ir.artapps.gamebrowser.ui.util.preferences.SharedPrefKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class PodRepositoryImpl(
    val context: Context,
    private val remote: PodRemoteDataSource
) : PodRepository {

    override var token: String? = null
    override var playPodProfile: PlayPodResponseLogin? = null
        set(value) {
            field = value
            SharedPref.DEFAULT.storeParcelable("playPodProfile", field)
        }
        get() {
            return SharedPref.DEFAULT.getParcelable(
                "playPodProfile",
                null,
                PlayPodResponseLogin::class.java
            )
        }

    override var refreshToken: String? = null
    override var profile: UserProfile? = null

    override val profileLiveData = MutableLiveData<UserProfile?>()

    override suspend fun updateToken() {

        try {
            if (refreshToken.isNullOrEmpty()) {
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

    override fun getUserProfile(): LiveData<UserProfile?> {
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

    override fun getUserProfile(tk: String): LiveData<UserProfile?> {
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
                getMeta()
                SharedPref.DEFAULT.storeParcelable(SharedPrefKeys.PROFILE, profile)
                profileLiveData.postValue(result)
                remote.follow(token!!)
            }
        }
        return profileLiveData
    }

    private fun getMeta(){
        runBlocking(Dispatchers.IO) {
            try {
                profile?.clientMetadata =
                    remote.getUserMeta(profile?.ssoId.toString())?.clientMetadata
                val meta = Gson().fromJson(profile?.clientMetadata, ClientMetadata::class.java)
                profile?.kidzyName = meta.kidzyName
                profile?.age = meta.age
                profile?.sex = meta.sex
                profile?.avatar = meta.avatar
            } catch (e: Exception) {
                profile?.kidzyName = profile?.username
            }
        }
    }

    override fun updateMeta(name: String, age: Int?, sex: String, avatar: Int) {
        val model = ClientMetadata().apply {
            kidzyName = name
            this.age = age
            this.sex = sex
            this.avatar = avatar
        }
        runBlocking(Dispatchers.IO) {
            try {
                profile?.ssoId?.let { remote.setUserMeta(it, Gson().toJson(model, ClientMetadata::class.java)) }
            } catch (e: Exception) { }

            getMeta()
            SharedPref.DEFAULT.storeParcelable(SharedPrefKeys.PROFILE, profile)
            profileLiveData.postValue(profile)
        }
    }

    override fun signOut() {
        token = ""
        refreshToken = ""
        profile = null
        SharedPref.DEFAULT.clear()
        profileLiveData.postValue(null)
    }
}
