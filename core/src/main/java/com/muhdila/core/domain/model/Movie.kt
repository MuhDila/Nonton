package com.muhdila.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    var id: Int,

    var title: String?,

    var overview: String?,

    var popularity: Float?,

    var poster_path: String?,

    var backdrop_path: String?,

    var vote_average: Float?,

    var release_date: String?,

    var isFavorite: Boolean = false

): Parcelable