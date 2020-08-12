package ir.artapps.gamebrowser.repo

import androidx.lifecycle.MutableLiveData
import ir.artapps.gamebrowser.entities.pod.UserProfile

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

interface PodRepository {
    suspend fun getUserProfile(token: String): UserProfile?
    suspend fun getUserProfile(): UserProfile?
    fun signOut()
    var profileLiveData: MutableLiveData<UserProfile?>
    var token: String?
    var profile: UserProfile?


    companion object{
        var token: String? = null
        var profile: UserProfile? = null
    }
}