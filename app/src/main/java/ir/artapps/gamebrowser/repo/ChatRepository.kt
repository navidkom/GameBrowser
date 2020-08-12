package ir.artapps.gamebrowser.repo

import androidx.lifecycle.LiveData
import com.fanap.podchat.mainmodel.MessageVO

interface ChatRepository {
    fun getHistoryLiveData(): LiveData<List<MessageVO>>
    fun sendMessage(message: String)
}