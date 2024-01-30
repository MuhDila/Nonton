package com.muhdila.core.data.source.remote.network

import com.muhdila.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getListMovie(): ListMovieResponse

}