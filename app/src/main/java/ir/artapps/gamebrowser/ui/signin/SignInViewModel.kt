package ir.artapps.gamebrowser.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _profileLiveData = MutableLiveData<UserProfile>()
    val profileLiveData : LiveData<UserProfile> = _profileLiveData

    fun getUserProfile(token: String) {
        viewModelScope.launch {
            _profileLiveData.value = repository.getUserProfile(token)
        }
    }
}