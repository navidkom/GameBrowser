package ir.artapps.gamebrowser

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 *   Created by Navid Komijani
 *   on 29,February,2020
 */
class App : MultiDexApplication() {

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

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
    }
}