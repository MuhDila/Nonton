package com.muhdila.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    var id: Int,

    var title: String?,

    var overview: String?,

    var popularity: Float?,

    var posterPath: String?,

    var backdropPath: String?,

    var voteAverage: Float?,

    var releaseDate: String?,

    var isFavorite: Boolean = false

): Parcelable