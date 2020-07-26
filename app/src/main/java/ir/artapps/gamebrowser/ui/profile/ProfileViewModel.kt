package ir.artapps.gamebrowser.ui.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanap.gameCenter.TIS.Base.RequestCallback
import com.fanap.gameCenter.TIS.Service
import com.fanap.gameCenter.TIS.Share.ConfigData
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.repo.GamesRepository
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by navid
 */
class ProfileViewModel(private val repository: GamesRepository, private val service: Service) : ViewModel() {

    fun isLoggedIn() : Boolean {
        return true
    }

}