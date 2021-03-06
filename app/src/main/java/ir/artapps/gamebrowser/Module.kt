package ir.artapps.gamebrowser

import androidx.room.Room
import com.fanap.gameCenter.TIS.Service
import ir.artapps.gamebrowser.local.AppDatabase
import ir.artapps.gamebrowser.remote.GamesRemoteDataSource
import ir.artapps.gamebrowser.remote.GamesRemoteDataSourceImpl
import ir.artapps.gamebrowser.remote.PodRemoteDataSource
import ir.artapps.gamebrowser.remote.PodRemoteDataSourceImpl
import ir.artapps.gamebrowser.repo.*
import ir.artapps.gamebrowser.ui.home.FavoriteViewModel
import ir.artapps.gamebrowser.ui.detail.DetailViewModel
import ir.artapps.gamebrowser.ui.home.HomeViewModel
import ir.artapps.gamebrowser.ui.main.MainViewModel
import ir.artapps.gamebrowser.ui.profile.ProfileViewModel
import ir.artapps.gamebrowser.ui.signin.SignInViewModel
import ir.artapps.gamebrowser.ui.social.SocialViewModel
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
        single<PodRemoteDataSource> { PodRemoteDataSourceImpl() }

//        single<Service> { Service.getInstance(JSONObject().put("context", androidApplication())) }

        single<PodRepository> { PodRepositoryImpl(androidApplication(), get()) }
        single<GamesRepository> { GamesRepositoryImpl(androidApplication(), get(), get(), get()) }
        single<ChatRepository> { ChatRepositoryImpl(androidApplication(), get()) }


        viewModel { FavoriteViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { DetailViewModel(get(), get()) }
        viewModel { ProfileViewModel(get()) }
        viewModel { SignInViewModel(get()) }
        viewModel { SocialViewModel(get(), get()) }
        viewModel { MainViewModel(get(), get()) }
    }
}