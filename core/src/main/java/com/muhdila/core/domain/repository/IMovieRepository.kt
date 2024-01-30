package com.muhdila.core.domain.repository

import com.muhdila.core.data.source.Resource
import com.muhdila.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getListMovie(): Flow<Resource<List<Movie>>>

    fun getListFavoriteMovie(): Flow<List<Movie>>

    fun setListFavoriteMovie(movie: Movie, state: Boolean)

}