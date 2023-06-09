package com.example.onboarding

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApis {
    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResp

    @GET("movie/top_rated")
    suspend fun topRateMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRateRes


}
