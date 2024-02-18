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
                poster_path = it.posterPath,
                backdrop_path = it.backdropPath,
                vote_average = it.voteAverage,
                release_date = it.releaseDate,
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
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path,
                voteAverage = it.vote_average,
                releaseDate = it.release_date,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        popularity = input.popularity,
        poster_path = input.posterPath,
        backdrop_path = input.backdropPath,
        vote_average = input.voteAverage,
        release_date = input.releaseDate,
        isFavorite = input.isFavorite
    )

}