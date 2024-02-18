package com.muhdila.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Float?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float?,

    @SerializedName("release_date")
    val releaseDate: String?,

)