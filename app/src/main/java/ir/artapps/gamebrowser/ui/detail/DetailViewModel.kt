package ir.artapps.gamebrowser.ui.detail

import android.graphics.Color
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.repo.GamesRepository
import ir.artapps.gamebrowser.repo.PodRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Created by navid
 */
class DetailViewModel(
    private val repository: GamesRepository,
    val podRepository: PodRepository
) :
    ViewModel() {

    var game: Game? = null

    private val _gameLiveData = MutableLiveData<Game>()
    val gameLiveData: LiveData<Game> = _gameLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getDetail(id: Long) {
        viewModelScope.launch {
            try {
                _gameLiveData.value = repository.getGame(game?.entityId.toString())
            } catch (e: Exception) {
                _errorLiveData.value = "AN ERROR OCCURRED"
            }
        }
    }

    fun getGamePlayURL(item: Game): LiveData<String> {


//        item.metadata?.isPlayPod = true
//        item.metadata?.url =
//            "https://playpod.ir/src/Static/Games/GameNo7557-41ovtmi2.nkk/index.html"

        val ld = MutableLiveData<String>()
        item.metadata?.let {
            if (!it.isPlayPod) {
                ld.postValue(item.metadata?.url)
            } else {
                val loginData = JSONObject().apply {
                    put("id", podRepository.playPodProfile?.userID)
                    put("token", podRepository.playPodProfile?.token)
                    put("tokenIssuer", 0)
                    put("name", podRepository.playPodProfile?.name)
                }

                val result = item.metadata?.url + "?gameData=" + Uri.encode(loginData.toString())
                Log.d("game URL:" , result)
                ld.postValue(result)

//                val reqData = JSONObject()
//                val gamesId = JSONArray()
//
//                gamesId.put(item.entityId!!)
//                reqData.put("gamesId", gamesId)
//                service.getGamesInfo(
//                    reqData,
//                    object : RequestCallback() {
//                        override fun onResult(result: JSONObject) {
//                            println("getEnrolledLeagues method : $result")
//
//                            val downloadLink =
//                                ConfigData.gwbu + "/" + ((result.get("result") as JSONObject).getJSONArray(
//                                    "games"
//                                )[0] as JSONObject).getString("physicalUrl") + "?gameData=" + Uri.encode(
//                                    loginData.toString()
//                                )
//                            ld.postValue(downloadLink)
//                        }
//                    })
            }
        }
        return ld
    }

    fun like() {
        viewModelScope.launch {
            repository.like(game?.id.toString(), false)
            try {
                _gameLiveData.value = repository.getGame(game?.entityId.toString())
            } catch (e: Exception) {
            }
        }
    }

    fun dislike() {
        viewModelScope.launch {
            repository.dislike(game?.id.toString(), true)
            try {
                _gameLiveData.value = repository.getGame(game?.entityId.toString())
            } catch (e: Exception) {
            }
        }
    }

    fun favorite(favorite: Boolean) {
        viewModelScope.launch {
            repository.favorite(game?.id.toString(), favorite)
            try {
                _gameLiveData.value = repository.getGame(game?.entityId.toString())
            } catch (e: Exception) {
            }
        }
    }


    fun rate(rate: Float) {
        viewModelScope.launch {
            repository.rate(game?.id.toString(), rate)
            try {
                _gameLiveData.value = repository.getGame(game?.entityId.toString())
            } catch (e: Exception) {
            }
        }
    }
}