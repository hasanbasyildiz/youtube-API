package com.example.youtubeapikotlin.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getService(): ApiServices {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(

                    HttpLoggingInterceptor.Level.NONE
                )

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val url = chain
                        .request()
                        .url
                        .newBuilder()
                        .addQueryParameter("key", "AIzaSyC8EHCOiOMVGihT-mEfIfERonBDOkNsYmk")
                        .build()
                    chain.proceed(chain.request().newBuilder().url(url).build())
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiServices::class.java)

        }
    }

}