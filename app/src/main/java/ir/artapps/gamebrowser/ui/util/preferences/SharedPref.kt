package ir.artapps.gamebrowser.ui.util.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import com.google.gson.Gson
import ir.artapps.gamebrowser.App
import ir.artapps.gamebrowser.entities.pod.UserProfile
import java.util.*

/**
 * Created by navid on 19,September,2018
 */
enum class SharedPref {
    DEFAULT;

    val cache = HashMap<String, Any?>()
    val preferences: SharedPreferences? = App.context?.getSharedPreferences(
        name.toLowerCase(),
        Context.MODE_PRIVATE
    )

    //region GET
    fun getBoolean(key: String, defaultValue: Boolean): Boolean? {
        if (cache.containsKey(key)) {
            return cache[key] as Boolean
        }
        val value = preferences?.getBoolean(key, defaultValue)
        cache[key] = value
        return value
    }

    fun getInt(key: String, defaultValue: Int): Int? {
        if (cache.containsKey(key)) {
            return cache[key] as Int
        }
        val value = preferences?.getInt(key, defaultValue)
        cache[key] = value
        return value
    }

    fun getLong(key: String, defaultValue: Long): Long? {
        if (cache.containsKey(key)) {
            return cache[key] as Long
        }
        val value = preferences?.getLong(key, defaultValue)
        cache[key] = value
        return value
    }

    fun getFloat(key: String, defaultValue: Float): Float? {
        if (cache.containsKey(key)) {
            return cache[key] as Float
        }
        val value = preferences?.getFloat(key, defaultValue)
        cache[key] = value
        return value
    }

    fun getString(key: String, defaultValue: String?): String? {
        if (cache.containsKey(key)) {
            return cache[key] as String?
        }
        val value = preferences?.getString(key, defaultValue)
        cache[key] = value
        return value
    }

    fun<T : Parcelable> getParcelable (key: String, defaultValue: T?, classOfT: Class<T>): T? {
        val result = if (cache.containsKey(key)) {
             cache[key] as String?
        } else {
            preferences?.getString(key, "")
        }
        return try {
            Gson().fromJson(result, classOfT)
        } catch (e:Exception) {
            defaultValue
        }
    }

    fun getStringSet(
        key: String,
        defaultValue: Set<String?>?
    ): Set<String>? {
        if (cache.containsKey(key)) {
            return cache[key] as Set<String>?
        }
        val value =
            preferences?.getStringSet(key, defaultValue)
        cache[key] = value
        return value
    }

    //endregion
    //region PUT
    fun storeBoolean(key: String, value: Boolean): Boolean? {
        cache[key] = value
        return preferences?.edit()?.putBoolean(key, value)?.commit()
    }

    fun storeInt(key: String, value: Int): Boolean? {
        cache[key] = value
        return preferences?.edit()?.putInt(key, value)?.commit()
    }

    fun storeLong(key: String, value: Long): Boolean? {
        cache[key] = value
        return preferences?.edit()?.putLong(key, value)?.commit()
    }

    fun storeFloat(key: String, value: Float): Boolean? {
        cache[key] = value
        return preferences?.edit()?.putFloat(key, value)?.commit()
    }

    fun storeString(key: String, value: String?): Boolean? {
        cache[key] = value
        return preferences?.edit()?.putString(key, value)?.commit()
    }

    fun storeParcelable(key: String, value: Parcelable?): Boolean? {
        val json = Gson().toJson(value)
        cache[key] = json
        return preferences?.edit()?.putString(key, json)?.commit()
    }

    fun storeStringSet(
        key: String,
        value: Set<String?>?
    ): Boolean? {
        cache[key] = value
        return preferences?.edit()?.putStringSet(key, value)?.commit()
    }

    //endregion
    fun remove(key: String): Boolean? {
        cache.remove(key)
        return preferences?.edit()?.remove(key)?.commit()
    }

    fun clear() {
        cache.clear()
        preferences?.edit()?.clear()?.apply()
    }
}