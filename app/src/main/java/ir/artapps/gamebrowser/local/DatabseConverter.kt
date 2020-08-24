package ir.artapps.gamebrowser.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.UserPostInfo
import ir.artapps.gamebrowser.entities.product.Business
import ir.artapps.gamebrowser.entities.product.Currency
import ir.artapps.gamebrowser.entities.product.GameMetaData

class DatabaseConverter {
    val gson = Gson()
    @TypeConverter
    fun businessToString(business: Business?): String {
        return gson.toJson(business)
    }

    @TypeConverter
    fun stringToBusiness(data: String?): Business {
        val type = object : TypeToken<Business?>() {}.type

        var result = Business()
        try {
            result = gson.fromJson(data, type)
        } catch (e: Exception){ }
        return  result
    }

    @TypeConverter
    fun currencyToString(lobby: Currency?): String {
        return gson.toJson(lobby)
    }

    @TypeConverter
    fun stringToCurrency(data: String?): Currency {
        val type = object : TypeToken<Currency?>() {}.type

        var result = Currency()
        try {
            result = gson.fromJson(data, type)
        } catch (e: Exception){ }
        return  result
    }

    @TypeConverter
    fun rateToString(rate: Rate?): String {
        return gson.toJson(rate)
    }

    @TypeConverter
    fun stringToRate(data: String?): Rate {
        val type = object : TypeToken<Rate?>() {}.type

        var result = Rate()
        try {
            result = gson.fromJson(data, type)
        } catch (e: Exception){ }
        return  result

    }

    @TypeConverter
    fun userPostInfoToString(userPostInfo: UserPostInfo?): String {
        return gson.toJson(userPostInfo)
    }

    @TypeConverter
    fun stringToUserPostInfo(data: String?): UserPostInfo {
        val type = object : TypeToken<UserPostInfo?>() {}.type

        var result = UserPostInfo()
        try {
            result = gson.fromJson(data, type)
        } catch (e: Exception){ }
        return  result
    }

    @TypeConverter
    fun stringArrayToString(array: List<String>?): String {
        return gson.toJson(array)
    }

    @TypeConverter
    fun stringToStringArray(data: String?): List<String> {
        val type = object : TypeToken<List<String>>() {}.type

        var result = listOf<String>()
        try {
            result = gson.fromJson(data, type)
        } catch (e: Exception){ }
        return  result
    }

    @TypeConverter
    fun gameMetaToString(game: GameMetaData?): String {
        return gson.toJson(game)
    }

    @TypeConverter
    fun stringToGameMeta(data: String?): GameMetaData {
        val type = object : TypeToken<GameMetaData>() {}.type

        var result = GameMetaData()
        try {
            result = gson.fromJson(data, type)
        } catch (e: Exception){ }
        return  result
    }
}