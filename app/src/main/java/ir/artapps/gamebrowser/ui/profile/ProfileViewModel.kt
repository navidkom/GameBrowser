package ir.artapps.gamebrowser.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.repo.PodRepository

/**
 * Created by navid
 */
class ProfileViewModel(val repository: PodRepository, private val service: Service) :
    ViewModel() {

    var profileLiveData = repository.profileLiveData

    fun getUserProfile() {
        repository.getUserProfile()
    }

    fun signOut() {
        repository.signOut()
    }
}