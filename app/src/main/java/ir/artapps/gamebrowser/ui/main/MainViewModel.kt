package ir.artapps.gamebrowser.ui.main

import androidx.lifecycle.*
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.SingleLiveEvent
import ir.artapps.gamebrowser.entities.pod.GetProfileResponseModel
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.repo.ChatRepository
import ir.artapps.gamebrowser.repo.ChatRepositoryImpl
import ir.artapps.gamebrowser.repo.PodRepository
import kotlinx.coroutines.launch

/**
 * Created by navid
 */
class MainViewModel(val repository: PodRepository,val chatRepository: ChatRepository, private val service: Service) :
    ViewModel() {
    var profileLiveData = repository.profileLiveData

    fun getUserProfile() {
        repository.getUserProfile()
    }
}