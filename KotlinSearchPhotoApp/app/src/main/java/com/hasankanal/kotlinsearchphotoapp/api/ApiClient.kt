package com.hasankanal.kotlinsearchphotoapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val ACCESS_KEY = "Authorization: Client-ID NBOtxLhO9LZFoz12fT1yeZIkLVEIV7w68p2gGpDIarg"
    private val BASE_URL = "https://api.unsplash.com/"

    fun getClient() : Retrofit{
        val intepceptor = HttpLoggingInterceptor()
        intepceptor.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(intepceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit
    }
}