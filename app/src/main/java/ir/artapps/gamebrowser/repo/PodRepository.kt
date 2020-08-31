package ir.artapps.gamebrowser.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.artapps.gamebrowser.SingleLiveEvent
import ir.artapps.gamebrowser.entities.PlayPodResponseLogin
import ir.artapps.gamebrowser.entities.pod.UserProfile

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

interface PodRepository {

    val profileLiveData : LiveData<UserProfile?>
    suspend fun updateToken(){}
    fun getUserProfile(token: String): LiveData<UserProfile?>
    fun getUserProfile(): LiveData<UserProfile?>
    fun signOut()
    fun updateMeta(name: String, age: Int?, sex: String, avatar: Int)

    //    var profileLiveData: MutableLiveData<UserProfile?>
//    var signedInLiveData: SingleLiveEvent<Nothing>
    var token: String?
    var playPodProfile: PlayPodResponseLogin?
    var profile: UserProfile?
    var refreshToken: String?
}