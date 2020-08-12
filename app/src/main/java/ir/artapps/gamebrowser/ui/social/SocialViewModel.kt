package ir.artapps.gamebrowser.ui.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fanap.podchat.mainmodel.MessageVO
import ir.artapps.gamebrowser.repo.ChatRepository
import ir.artapps.gamebrowser.repo.PodRepository

class SocialViewModel(private val chatRepository: ChatRepository, private val podRepository: PodRepository) : ViewModel(){

    val profileLiveData = podRepository.profileLiveData
    val chatLiveData = chatRepository.getHistoryLiveData()

    fun sendMessage(message: String){
        chatRepository.sendMessage(message)
    }
}