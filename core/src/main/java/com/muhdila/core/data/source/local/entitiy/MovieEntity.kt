package com.muhdila.core.data.source.local.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(

    @PrimaryKey
    var id: Int,

    var title: String?,

    var overview: String?,

    var popularity: Float?,

    var poster_path: String?,

    var backdrop_path: String?,

    var vote_average: Float?,

    var release_date: String?,

    var isFavorite: Boolean = false

)
