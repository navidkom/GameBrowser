package ir.artapps.gamebrowser.repo

import androidx.lifecycle.LiveData
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.local.GamesDAO
import ir.artapps.gamebrowser.remote.GamesRemoteDataSource

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class GamesRepositoryImpl(
    private var gamesDAO: GamesDAO,
    private val remote: GamesRemoteDataSource
) : GamesRepository {

    val PAGE_SIZE = 20

    override fun getGamesLiveData(): LiveData<List<Game>> {
        return gamesDAO.loadAll()
    }

    override suspend fun getGames(firstPage: Boolean): Int {
        var games: List<Game> = listOf()
        var responseCode: Int
        try {

            // calculate first item number of next page that will request
            val remoteRespone = remote.getGames(
                PAGE_SIZE,
                if (firstPage) 0 else gamesDAO.getCount(),
                ""
            )

            remoteRespone.game?.let {
                games = it
            }

            // define response code 1
            // when response is ok but we get items less than one page size
            // this means end of list reached
            if (remoteRespone.hasError!!) {
                responseCode = 0
            } else {
                responseCode = when (games.size) {
                    PAGE_SIZE -> 200
                    0 -> 2
                    else -> 1
                }
            }

        } catch (e: Exception) {
            // if any exception occur that may because of timeout or inaccessibility to network
            // or other reasons set the response code to 0
            // we can define error codes in more details according to exception type but it is trivial.
            responseCode = 0
        }

        // persist data or clean db if response is ok
        if (responseCode == 200 || responseCode == 1) {
            if (firstPage) {
                gamesDAO.invalidate()
            }
            gamesDAO.insertGames(games)
        }

        return responseCode
    }

    override suspend fun getGame(gameId: String): Game {
        return gamesDAO.getGame(gameId)
    }
}