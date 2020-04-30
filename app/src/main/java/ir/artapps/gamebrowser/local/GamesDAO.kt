package ir.artapps.gamebrowser.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.artapps.gamebrowser.entities.Game

/**
 * Created by navid
 */
@Dao
interface GamesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGames(games: List<Game>)

    @Query("SELECT * FROM response order by id_ asc")
    fun loadAll(): LiveData<List<Game>>

    @Query("SELECT * FROM response where id = :gameId ")
    suspend fun getGame(gameId: String): Game

    @Query("SELECT COUNT(id) FROM response")
    suspend fun getCount(): Int

    @Query("DELETE FROM response")
    suspend fun invalidate()
}