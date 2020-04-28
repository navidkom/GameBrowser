package ir.artapps.gamebrowser.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   Created by Navid Komijani
 *   on 23,February,2020
 */
class ServiceGenerator {
    companion object {
        var BASE_URL = "https://service-play.pod.ir/"

        fun create(): FoursquareApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(FoursquareApi::class.java)
        }
    }
}