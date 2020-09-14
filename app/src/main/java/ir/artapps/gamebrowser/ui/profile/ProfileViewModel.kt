package ir.artapps.gamebrowser.ui.profile

import androidx.lifecycle.*
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.SingleLiveEvent
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.repo.PodRepository
import kotlinx.coroutines.launch

/**
 * Created by navid
 */
class ProfileViewModel(val repository: PodRepository) :
    ViewModel() {

    var tempAge = 0
    var tempSex = ""
    var tempAvatar = 0

    var profileLiveData = repository.profileLiveData
    val updateProfileLiveData = SingleLiveEvent<Boolean>()

    fun getUserProfile() {
        viewModelScope.launch {
            repository.getUserProfile()
        }
    }

    fun updateMeta(name: String, age:Int?, sex: String, avatar: Int ){
        viewModelScope.launch {
            val response = repository.updateMeta(name, age, sex, avatar)
            updateProfileLiveData.postValue(response)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
        }
    }
}