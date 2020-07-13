package ir.artapps.gamebrowser

import androidx.room.Room
import ir.artapps.gamebrowser.local.AppDatabase
import ir.artapps.gamebrowser.remote.GamesRemoteDataSource
import ir.artapps.gamebrowser.remote.GamesRemoteDataSourceImpl
import ir.artapps.gamebrowser.repo.GamesRepository
import ir.artapps.gamebrowser.repo.GamesRepositoryImpl
import ir.artapps.gamebrowser.ui.home.HomeViewModel
import ir.artapps.moviedb.ui.detail.DetailViewModel
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
        single<GamesRepository> { GamesRepositoryImpl(get(), get()) }

        viewModel { HomeViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}