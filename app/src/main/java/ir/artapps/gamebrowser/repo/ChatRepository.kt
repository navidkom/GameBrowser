package ir.artapps.gamebrowser.repo

import android.app.Activity
import androidx.lifecycle.LiveData
import com.fanap.podchat.mainmodel.MessageVO

interface ChatRepository {
    fun getHistoryLiveData(): LiveData<List<MessageVO>>
    fun getChatStateLiveData(): LiveData<String>
    fun sendMessage(message: String)
    fun setNotification(activity: Activity)
    fun shouldShowNotification(bool: Boolean)
}