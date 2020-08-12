package ir.artapps.gamebrowser.ui.main

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
class MainViewModel(private val repository: PodRepository, private val service: Service) :
    ViewModel() {

    val profileLiveData : LiveData<UserProfile?> = repository.profileLiveData

    fun getUserProfile() {
        viewModelScope.launch {
            repository.getUserProfile()
        }
    }

    fun signOut() {
        repository.signOut()
    }
}