package ir.artapps.gamebrowser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.repo.GamesRepository
import kotlinx.coroutines.launch

/**
 * Created by navid
 */
class VenueDetailViewModel(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _venueLiveData = MutableLiveData<Game>()
    val venueLiveData: LiveData<Game> = _venueLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getVenueDetail(venueId: String) {
        viewModelScope.launch {
            val response = gamesRepository.getGame(venueId)
//            if (response?.meta?.code == 200) {
//                _venueLiveData.value = response.response.venue
//            } else {
//                _errorLiveData.value = "AN ERROR OCCURRED"
//            }
        }
    }
}