package ir.artapps.gamebrowser.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.GetProfileResult
import ir.artapps.gamebrowser.repo.PodRepository
import kotlinx.coroutines.launch

/**
 * Created by navid
 */
class ProfileViewModel(private val repository: PodRepository, private val service: Service) :
    ViewModel() {

    private val _profileLiveData = MutableLiveData<GetProfileResponseModel>()
    val profileLiveData : LiveData<GetProfileResponseModel> = _profileLiveData


    fun getUserProfile() {
        viewModelScope.launch {
            _profileLiveData.postValue(repository.getUserProfile(App.token))
        }
    }
}