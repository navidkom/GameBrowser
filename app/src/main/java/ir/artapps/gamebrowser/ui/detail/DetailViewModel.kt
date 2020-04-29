package ir.artapps.moviedb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.repo.GamesRepository
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by navid
 */
class DetailViewModel(private val repository: GamesRepository) : ViewModel() {

    private val _movieLiveData = MutableLiveData<Game>()
    val movieLiveData: LiveData<Game> = _movieLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            try {
                _movieLiveData.value = repository.getGame(id.toString())
            } catch (e: Exception) {
                _errorLiveData.value = "AN ERROR OCCURRED"
            }
        }
    }
}