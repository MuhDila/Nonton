package com.muhdila.core.domain.useCase

import com.muhdila.core.data.source.Resource
import com.muhdila.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getListMovie(): Flow<Resource<List<Movie>>>

    fun getListFavoriteMovie(): Flow<List<Movie>>

    fun setListFavoriteMovie(movie: Movie, state: Boolean)

}