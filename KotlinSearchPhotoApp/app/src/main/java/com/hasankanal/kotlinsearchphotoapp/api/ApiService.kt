package com.hasankanal.kotlinsearchphotoapp.api

import com.hasankanal.kotlinsearchphotoapp.data.remote.Photos
import com.hasankanal.kotlinsearchphotoapp.data.remote.SearchImage
import com.hasankanal.kotlinsearchphotoapp.data.remote.SingleImage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(ApiClient.ACCESS_KEY)
    @GET("photos")
    fun getPhotosFromApi(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<List<Photos>>

    @Headers(ApiClient.ACCESS_KEY)
    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<SearchImage>

    @Headers(ApiClient.ACCESS_KEY)
    @GET("photos/random")
    fun randomPhoto(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int

    ): Observable<List<Photos>>
}