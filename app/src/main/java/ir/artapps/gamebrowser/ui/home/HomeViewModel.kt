package ir.artapps.gamebrowser.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.repo.GamesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val gamesRepository: GamesRepository, private val service: Service) : ViewModel() {

    val gamesLiveData: LiveData<List<Game>> = gamesRepository.getGamesLiveData()
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    var loading = false
    var lastPage = false

    // this method requests games from repository and handle returned error
    // result of games will post on gamesLiveData and we dose not get result here
    fun getGames(
        firstPage: Boolean, filter: String? = null
    ) {
        if (firstPage) lastPage = false
        viewModelScope.launch {
            val responseCode = gamesRepository.getGames(firstPage, filter)

            // check if this is last page or not
            if (responseCode == 1 || responseCode == 2) {
                lastPage = true
            }

            // error cases
            if (responseCode != 200 && responseCode != 1) {
                delay(3000)
                _errorLiveData.postValue(
                    when (responseCode) {
                        0 -> "AN ERROR OCCURRED: Network Error"
                        2 -> if(firstPage) "LIST IS EMPTY" else "NOTHING ADDED"
                        else -> "AN ERROR OCCURRED: Error Code $responseCode"
                    }
                )
            }
        }
    }
}

