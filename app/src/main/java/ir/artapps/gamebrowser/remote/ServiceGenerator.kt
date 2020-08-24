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
        var PLAYPOD_BASE_URL = "https://service-play.pod.ir/"
        var POD_Account_BASE_URL = "https://accounts.pod.ir"
        var POD_SANDBOX_BASE_URL = "http://sandbox.pod.ir:8080"


        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        fun createPlayPodService(): PlayPodApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(PLAYPOD_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PlayPodApi::class.java)
        }

        fun createPodAccountService(): PodApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(POD_Account_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PodApi::class.java)
        }

        fun createPodSandboxService(): PodApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(POD_SANDBOX_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PodApi::class.java)
        }
    }
}