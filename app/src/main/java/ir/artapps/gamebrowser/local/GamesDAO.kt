package ir.artapps.gamebrowser.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.entities.product.ProductGame

/**
 * Created by navid
 */
@Dao
interface GamesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGames(games: List<Game>)

    @Query("SELECT * FROM games order by id desc")
    suspend fun loadAll(): List<Game>

    @Query("SELECT * FROM games where id = :gameId ")
    suspend fun getGame(gameId: String): Game

    @Query("SELECT COUNT(id) FROM games")
    suspend fun getCount(): Int

    @Query("DELETE FROM games")
    suspend fun invalidate()
}