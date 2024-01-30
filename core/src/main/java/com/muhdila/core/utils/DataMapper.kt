package com.muhdila.core.utils

import com.muhdila.core.domain.model.Movie
import com.muhdila.core.data.source.local.entitiy.MovieEntity
import com.muhdila.core.data.source.remote.response.ListMovieResponse
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
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                vote_average = it.vote_average,
                release_date = it.release_date,
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
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                vote_average = it.vote_average,
                release_date = it.release_date,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        popularity = input.popularity,
        poster_path = input.poster_path,
        backdrop_path = input.backdrop_path,
        vote_average = input.vote_average,
        release_date = input.release_date,
        isFavorite = input.isFavorite
    )

}