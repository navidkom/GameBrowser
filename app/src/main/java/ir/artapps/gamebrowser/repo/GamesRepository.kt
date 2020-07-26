package ir.artapps.gamebrowser.repo

import androidx.lifecycle.LiveData
import com.fanap.gameCenter.TIS.Base.RequestCallback
import ir.artapps.gamebrowser.entities.Game

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

interface GamesRepository {
     fun getGamesLiveData(): LiveData<List<Game>>
     suspend fun getGames(firstPage: Boolean, filter: String?): Int
     suspend fun getGame(gameId: String): Game
}