package ir.artapps.gamebrowser.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.artapps.gamebrowser.entities.Game

/**
 * Created by navid
 */

@TypeConverters(DatabaseConverter::class)
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val gamesDAO: GamesDAO
}