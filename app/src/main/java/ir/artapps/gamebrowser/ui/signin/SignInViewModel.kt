package ir.artapps.gamebrowser.ui.signin

import androidx.lifecycle.*
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.SingleLiveEvent
import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.repo.PodRepository
import kotlinx.coroutines.launch

/**
 * Created by navid
 */
class SignInViewModel( val repository: PodRepository) :
    ViewModel() {

    var profileLiveData = repository.profileLiveData

    fun getUserProfile(token: String) {
        viewModelScope.launch {
            repository.getUserProfile(token)
        }
    }

//    fun getUserProfile() {
//        profileLiveData = repository.getUserProfile()
//    }
}