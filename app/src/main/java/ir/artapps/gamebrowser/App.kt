package ir.artapps.gamebrowser

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import ir.artapps.gamebrowser.entities.pod.UserProfile
import ir.artapps.gamebrowser.ui.util.preferences.SharedPref
import ir.artapps.gamebrowser.ui.util.preferences.SharedPrefKeys
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *   Created by Navid Komijani
 *   on 29,February,2020
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(Module().appModule)
        }
    }

    companion object {
        var context: Context? = null
        var instance = App()
    }
}