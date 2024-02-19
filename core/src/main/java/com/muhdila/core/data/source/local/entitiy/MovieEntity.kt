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

    var posterPath: String?,

    var backdropPath: String?,

    var voteAverage: Float?,

    var releaseDate: String?,

    var isFavorite: Boolean = false

)
