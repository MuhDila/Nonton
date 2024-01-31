package com.muhdila.core.data.source.remote.network

import com.muhdila.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getListMovie(): ListMovieResponse

    // TODO Search
    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String?): ListMovieResponse

}