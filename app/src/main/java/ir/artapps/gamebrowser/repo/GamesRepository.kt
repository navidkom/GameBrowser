package ir.artapps.gamebrowser.repo

import androidx.lifecycle.LiveData
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.product.GeneralResponseModel

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

interface GamesRepository {
    fun getGamesLiveData(): LiveData<List<Game>>
    suspend fun getGames(firstPage: Boolean, filter: String?, auth: Boolean? = null ): Int
    suspend fun getGame(gameId: String): Game
    suspend fun getFavorites(): List<Game>
    suspend fun like(postId: String, dislike: Boolean): GeneralResponseModel<Boolean>
    suspend fun dislike(
        postId: String,
        dislike: Boolean
    ): GeneralResponseModel<Boolean>

    suspend fun favorite(postId: String, favorite: Boolean): GeneralResponseModel<Boolean>
    suspend fun rate(postId: String, rate: Float): GeneralResponseModel<Rate>
}