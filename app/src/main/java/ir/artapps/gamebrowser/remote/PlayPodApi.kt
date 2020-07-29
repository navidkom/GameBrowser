package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by navid
 */
interface PlayPodApi {
    companion object {
        const val SIZE_KEY = "size"
        const val OFFSET_KEY = "offset"
        const val FILTER_KEY = "filter"
    }

    @GET("srv/game/get")
    suspend fun fetchGamesAsync(
        @Query(SIZE_KEY) size: Int?,
        @Query(OFFSET_KEY) offset: Int?,
        @Query(FILTER_KEY) filter: String?
    ): ResponseModel
}