package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.ResponseModel

/**
 * Created by navid
 */
interface GamesRemoteDataSource {
    suspend fun getGames(
        size: Int,
        offset: Int,
        filter: String
    ): ResponseModel
}