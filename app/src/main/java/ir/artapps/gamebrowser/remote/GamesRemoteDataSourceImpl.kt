package ir.artapps.gamebrowser.remote

import ir.artapps.gamebrowser.entities.Rate
import ir.artapps.gamebrowser.entities.product.FavoriteResponse
import ir.artapps.gamebrowser.entities.product.GeneralResponseModel
import ir.artapps.gamebrowser.entities.product.ProductGame
import ir.artapps.gamebrowser.entities.product.Products

/**
 * Created by navid
 */
class GamesRemoteDataSourceImpl : GamesRemoteDataSource {
    val service by lazy {
        ServiceGenerator.createPodSandboxService()
    }

    val TOKEN_ISSUER = "1"
    val BUSINESS_ID = "18463"

    override suspend fun getGames(size: Int, offset: Int): GeneralResponseModel<List<ProductGame>> {
        return service.getProductList("application/json", BUSINESS_ID, size, offset, null)
    }

    override suspend fun getGame(
        token: String,
        productId: String?,
        size: Int,
        offset: Int
    ): GeneralResponseModel<Products> {
        return service.searchProduct(token, TOKEN_ISSUER,"application/json", BUSINESS_ID, offset, size, productId)
    }

    override suspend fun getFavorites(
        token: String,
        size: Int,
        offset: Int
    ): GeneralResponseModel<List<FavoriteResponse>> {
        return service.getFavorites(token, TOKEN_ISSUER,"application/json", size, offset)
    }

    override suspend fun like(
        token: String,
        postId: String,
        dislike: Boolean
    ): GeneralResponseModel<Boolean> {
        return service.likeProduct(token, TOKEN_ISSUER, postId, dislike)
    }

    override suspend fun dislike(
        token: String,
        postId: String,
        dislike: Boolean
    ): GeneralResponseModel<Boolean> {
        return service.dislikeProduct(token, TOKEN_ISSUER, postId, dislike)
    }

    override suspend fun favorite(
        token: String,
        postId: String,
        favorite: Boolean
    ): GeneralResponseModel<Boolean> {
        return service.favoriteProduct(token, TOKEN_ISSUER, postId, favorite)
    }

    override suspend fun rate(
        token: String,
        postId: String,
        rate: Float
    ): GeneralResponseModel<Rate> {
        return service.rateProduct(token, TOKEN_ISSUER, postId, rate)
    }
}