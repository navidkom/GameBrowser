package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.ResponseModel

/**
 * Created by navid
 */
class GamesRemoteDataSourceImpl: GamesRemoteDataSource {
    val service by lazy {
        ServiceGenerator.create()
    }

    override suspend fun getGames(size: Int, offset: Int, filter: String): ResponseModel {
        return service.fetchGamesAsync(size, offset, filter)
    }
}