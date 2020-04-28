package ir.artapps.gamebrowser.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.artapps.gamebrowser.entities.Business
import ir.artapps.gamebrowser.entities.Lobby
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.UserPostInfo

class DatabaseConverter {
    val gson = Gson()
    @TypeConverter
    fun businessToString(business: Business?): String {
        return gson.toJson(business)
    }

    @TypeConverter
    fun stringToBusiness(data: String?): Business {
        val type = object : TypeToken<Business?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun lobbyToString(lobby: Lobby?): String {
        return gson.toJson(lobby)
    }

    @TypeConverter
    fun stringToLobby(data: String?): Lobby {
        val type = object : TypeToken<Lobby?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun rateToString(rate: Rate?): String {
        return gson.toJson(rate)
    }

    @TypeConverter
    fun stringToRate(data: String?): Rate {
        val type = object : TypeToken<Rate?>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun userPostInfoToString(userPostInfo: UserPostInfo?): String {
        return gson.toJson(userPostInfo)
    }

    @TypeConverter
    fun stringToUserPostInfo(data: String?): UserPostInfo {
        val type = object : TypeToken<UserPostInfo?>() {}.type
        return gson.fromJson(data, type)
    }
}