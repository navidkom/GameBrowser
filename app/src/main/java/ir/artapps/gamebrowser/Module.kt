package ir.artapps.gamebrowser

import androidx.room.Room
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.local.AppDatabase
import ir.artapps.gamebrowser.remote.GamesRemoteDataSource
import ir.artapps.gamebrowser.remote.GamesRemoteDataSourceImpl
import ir.artapps.gamebrowser.repo.GamesRepository
import ir.artapps.gamebrowser.repo.GamesRepositoryImpl
import ir.artapps.gamebrowser.ui.home.HomeViewModel
import ir.artapps.gamebrowser.ui.detail.DetailViewModel
import ir.artapps.gamebrowser.ui.profile.ProfileViewModel
import org.json.JSONObject
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *   Created by Navid Komijani
 *   on 29,February,2020
 */

class Module {
    val appModule = module {
        single {
            Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "database.db")
                .build()
        }
        single { get<AppDatabase>().gamesDAO }
        single<GamesRemoteDataSource> { GamesRemoteDataSourceImpl() }

        single<Service> { Service.getInstance(JSONObject().put("context", androidApplication())) }

        single<GamesRepository> { GamesRepositoryImpl( androidApplication(), get(), get()) }


        viewModel { HomeViewModel(get(), get()) }
        viewModel { DetailViewModel(get(), get()) }
        viewModel { ProfileViewModel(get(), get()) }
    }
}