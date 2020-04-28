package ir.artapps.gamebrowser.repo

import androidx.lifecycle.LiveData
import ir.artapps.gamebrowser.entities.Game
import ir.artapps.gamebrowser.local.GamesDAO
import ir.artapps.gamebrowser.remote.GamesRemoteDataSource

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */

class GamesRepositoryImpl(
    private var gamesDAO: GamesDAO,
    private val remote: GamesRemoteDataSource
) : GamesRepository {

    final val PAGE_SIZE = 20

    override fun getGamesLiveData(): LiveData<List<Game>> {
        return gamesDAO.loadAll()
    }

    override suspend fun getGames(firstPage: Boolean): Int {

        var games: List<Game> = listOf()
        var responseCode: Int
        try {

            // calculate first item number of next page that will request
            val remoteRespone = remote.getGames(
                PAGE_SIZE,
                if (firstPage) 0 else gamesDAO.getCount(),
                ""
            )

            remoteRespone.game?.let {
                games = it
            }

            // define response code 1
            // when response is ok but we get items less than one page size
            // this means end of list reached
            if(remoteRespone.hasError!!){
                responseCode = 0
            } else {

                if (games.size < PAGE_SIZE) {
                    responseCode = 1
                } else {
                    responseCode = 200
                }
            }

        } catch (e: Exception) {
            // if any exception occur that may because of timeout or inaccessibility to network
            // or other reasons set the response code to 0
            // we can define error codes in more details according to exception type but it is trivial.
            responseCode = 0
        }

        // persist data or clean db if response is ok
        if (responseCode == 200 || responseCode == 1) {
            if (firstPage) {
                gamesDAO.invalidate()
            }
            gamesDAO.insertGames(games)
        }

        return responseCode
    }

    override suspend fun getGame(gameId: String): Game {
        return gamesDAO.getGame(gameId)
    }


    // get liveData of venues from room and this livedata observe any changes in venues table
//    override fun getVenuesLiveData(): LiveData<List<Venue>> {
//        return venuesDAO.loadAll().map { value ->
//            value.map {
//                it.toVenue()
//            }
//        }
//    }

    // this method handle caching strategy
    // our overall approach is getting 1 page of data from server and store it in db, after store
    // room will automatically update venuesLiveData and in this way viewmodel and fragment will update.
    // and for next page we check the db and request next page of data from there we store before.
    //
    // if parameter 'firstPage' be true it means that we will invalidate all data we stored before
    // and renew it (in change of user location or when user pull screen to refresh), in this case we
    // clean the database and get data from page 1
//    override suspend fun getNearVenues(
//        lat: Double,
//        lang: Double,
//        firstPage: Boolean
//    ): Int {
//
//        var venues: List<VenueRoomEntity> = listOf()
//        var responseCode: Int
//        try {
//
//            // calculate first item number of next page that will request
//            val remoteRespone = remote.getVenues(
//                lat,
//                lang,
//                if (firstPage) 0 else venuesDAO.getCount()
//            )
//
//            venues = remoteRespone.response.groups!![0].items!!.map {
//                VenueRoomEntity(it.venue)
//            }
//
//            // define response code 1
//            // when response is ok but we get items less than one page size
//            // this means end of list reached
//            if (remoteRespone.meta.code == 200 && venues.size < 20) {
//                responseCode = 1
//            } else {
//                responseCode = remoteRespone.meta.code
//            }
//
//        } catch (e: Exception) {
//            // if any exception occur that may because of timeout or inaccessibility to network
//            // or other reasons set the response code to 0
//            // we can define error codes in more details according to exception type but it is trivial.
//            responseCode = 0
//        }
//
//        // persist data or clean db if response is ok
//        if (responseCode == 200 || responseCode == 1) {
//            if (firstPage) {
//                venuesDAO.invalidate()
//            }
//            venuesDAO.insertVenues(venues)
//        }
//
//        return responseCode
//    }


}