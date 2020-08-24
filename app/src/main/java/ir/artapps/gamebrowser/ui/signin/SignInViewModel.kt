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
class SignInViewModel(private val repository: PodRepository, private val service: Service) :
    ViewModel() {

    var profileLiveData : LiveData<UserProfile?> = repository.profileLiveData

    fun getUserProfile(token: String) {
            repository.getUserProfile(token)
    }

//    fun getUserProfile() {
//        profileLiveData = repository.getUserProfile()
//    }
}