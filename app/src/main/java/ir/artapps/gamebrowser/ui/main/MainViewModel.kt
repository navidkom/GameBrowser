package ir.artapps.gamebrowser.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.repo.GamesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    val venuesLiveData: LiveData<List<Game>> = gamesRepository.getGamesLiveData()
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    var loading = false
    var lastPage = false

    // this method requests venues from repository and handle returned error
    // result of venus will post on venuesLiveData and we dose not get result here
    fun getVenues(
        firstPage: Boolean
    ) {

        if (firstPage) lastPage = false
        viewModelScope.launch {
            val responseCode = gamesRepository.getGames(firstPage)

            // check if this is last page or not
            if (responseCode == 1) {
                lastPage = true
            }

            // error cases
            if (responseCode != 200 && responseCode != 1) {
                delay(3000)
                _errorLiveData.postValue(
                    when (responseCode) {
                        0 -> "AN ERROR OCCURRED: Network Error"
                        else -> "AN ERROR OCCURRED: Error Code $responseCode"
                    }
                )
            }
        }
    }
}

