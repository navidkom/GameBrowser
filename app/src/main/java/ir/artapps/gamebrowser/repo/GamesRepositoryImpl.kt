package ir.artapps.gamebrowser.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.artapps.gamebrowser.R
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.product.GameMetaData
import ir.artapps.gamebrowser.entities.product.GeneralResponseModel
import ir.artapps.gamebrowser.entities.product.ProductGame
import ir.artapps.gamebrowser.local.GamesDAO
import ir.artapps.gamebrowser.remote.GamesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class GamesRepositoryImpl(
    val context: Context,
    private var gamesDAO: GamesDAO,
    private val remote: GamesRemoteDataSource,
    private val podRepository: PodRepository
) : GamesRepository {

    val PAGE_SIZE = 30

    override suspend fun like(
        postId: String,
        dislike: Boolean
    ): GeneralResponseModel<Boolean> {
        return remote.like(podRepository.token!!, postId, dislike)
    }

    override suspend fun dislike(
        postId: String,
        dislike: Boolean
    ): GeneralResponseModel<Boolean> {
        return remote.dislike(podRepository.token!!, postId, dislike)
    }

    override suspend fun favorite(
        postId: String,
        favorite: Boolean
    ): GeneralResponseModel<Boolean> {
        return remote.favorite(podRepository.token!!, postId, favorite)
    }

    override suspend fun rate(
        postId: String,
        rate: Float
    ): GeneralResponseModel<Rate> {
        return remote.rate(podRepository.token!!, postId, rate)
    }


    private var _gamesLiveData: MutableLiveData<List<Game>> = MutableLiveData()
    override fun getGamesLiveData(): LiveData<List<Game>> {
        return _gamesLiveData
    }

    override suspend fun getFavorites(): List<Game> {
        return remote.getFavorites(
            podRepository.token!!,
            size = 100,
            offset = 0
        ).product!!.map { it.item.convertToGame() }
    }

    private fun getGames() {
        runBlocking(Dispatchers.IO) {
            _gamesLiveData.postValue(gamesDAO.loadAll())
        }
    }

    override suspend fun getGames(firstPage: Boolean, filter: String?, auth: Boolean?): Int {
        var games: List<Game> = listOf()
        var responseCode: Int = 0
        var _firstPage = firstPage
        if (!filter.isNullOrEmpty()) {
            _firstPage = true
        }

        try {
            val remoteRespone =
                remote.getGames(
                    PAGE_SIZE,
                    if (_firstPage) 0 else gamesDAO.getCount()
                )


            remoteRespone?.product?.let { it ->
                games = it.map { it.convertToGame() }
            }

            // define response code 1
            // when response is ok but we get items less than one page size
            // this means end of list reached
            if (remoteRespone?.hasError!!) {
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
            e.printStackTrace()
            responseCode = 0
        }

        // persist data or clean db if response is ok
        if (responseCode == 200 || responseCode == 1 || responseCode == 2) {
            if (_firstPage) {
                gamesDAO.invalidate()
            }
            gamesDAO.insertGames(games)
            getGames()
        }
        return responseCode
    }

    override suspend fun getGame(
        gameId: String
    ): Game {
        var game = Game()
        try {
            game = remote.getGame(podRepository.token!!, gameId).product?.products?.get(0)
                ?.convertToGame()!!
            gamesDAO.insertGames(listOf(game))
            getGames()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return game
    }
}

fun ProductGame.convertToGame(): Game {
    return Game().also {

        it.id = id
        it.version = version
        it.entityId = entityId
        it.numOfLikes = numOfLikes
        it.numOfDisLikes = numOfDisLikes
        it.numOfShare = numOfShare
        it.numOfFavorites = numOfFavorites
        it.numOfComments = numOfComments
        it.timestamp = timestamp
        it.enable = enable
        it.hide = hide
        it.rate = rate
        it.canComment = canComment
        it.canLike = canLike
        it.canRate = canRate
        it.tags = tags
        it.name = name
        it.description = description
        it.userPostInfo = userPostInfo

        it.metadata = GameMetaData()

        try {
            val type = object : TypeToken<GameMetaData?>() {}.type
            it.metadata = Gson().fromJson(metadata, type)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        this.entityId?.let {tmp ->
            it.color = when (tmp % 4) {
                0 -> R.color.color1
                1 -> R.color.color2
                2 -> R.color.color3
                3 -> R.color.color4
                else -> R.color.color1
            }

            it.colorDark = when (tmp % 4) {
                0 -> R.color.color1Dark
                1 -> R.color.color2Dark
                2 -> R.color.color3Dark
                3 -> R.color.color4Dark
                else -> R.color.color1Dark
            }

            it.colorTransparent = when (tmp % 4) {
                0 -> R.color.color1Transparent
                1 -> R.color.color2Transparent
                2 -> R.color.color3Transparent
                3 -> R.color.color4Transparent
                else -> R.color.color1Transparent
            }
        }
    }
}