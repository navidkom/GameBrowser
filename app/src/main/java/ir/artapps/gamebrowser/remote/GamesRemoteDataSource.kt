package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.product.FavoriteResponse
import ir.artapps.gamebrowser.entities.product.ProductGame
import ir.artapps.gamebrowser.entities.product.GeneralResponseModel
import ir.artapps.gamebrowser.entities.product.Products

/**
 * Created by navid
 */
interface GamesRemoteDataSource {
    suspend fun getGames(
        size: Int,
        offset: Int
    ): GeneralResponseModel<List<ProductGame>>

    suspend fun like(token: String, postId: String, dislike: Boolean): GeneralResponseModel<Boolean>
    suspend fun dislike(
        token: String,
        postId: String,
        dislike: Boolean
    ): GeneralResponseModel<Boolean>

    suspend fun favorite(
        token: String,
        postId: String,
        favorite: Boolean
    ): GeneralResponseModel<Boolean>

    suspend fun rate(token: String, postId: String, rate: Float): GeneralResponseModel<Rate>
    suspend fun getGame(
        token: String,
        productId: String? = null,
        size: Int = 100,
        offset: Int = 0
    ): GeneralResponseModel<Products>

    suspend fun getFavorites(
        token: String,
        size: Int,
        offset: Int
    ): GeneralResponseModel<List<FavoriteResponse>>
}