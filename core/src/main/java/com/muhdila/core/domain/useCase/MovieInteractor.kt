package com.muhdila.core.domain.useCase

import com.muhdila.core.data.source.Resource
import com.muhdila.core.domain.model.Movie
import com.muhdila.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val iMovieRepository: IMovieRepository) : MovieUseCase {

    override fun getListMovie() = iMovieRepository.getListMovie()

    override fun getListFavoriteMovie() = iMovieRepository.getListFavoriteMovie()

    override fun setListFavoriteMovie(movie: Movie, state: Boolean) =
        iMovieRepository.setListFavoriteMovie(movie, state)

    // TODO Search
    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> =
        iMovieRepository.searchMovies(query)

}