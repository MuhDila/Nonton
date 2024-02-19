package com.muhdila.core.utils

import com.muhdila.core.domain.model.Movie
import com.muhdila.core.data.source.local.entitiy.MovieEntity
import com.muhdila.core.data.source.remote.response.MovieResponse

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val userGithubList = ArrayList<MovieEntity>()
        input.map {
            val userGithub = MovieEntity(
                id = it.id ?: 0,
                title = it.title,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                isFavorite = false
            )
            userGithubList.add(userGithub)
        }
        return userGithubList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite
    )

}