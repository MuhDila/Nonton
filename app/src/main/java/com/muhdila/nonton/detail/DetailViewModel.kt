package com.muhdila.nonton.detail

import androidx.lifecycle.ViewModel
import com.muhdila.core.domain.model.Movie
import com.muhdila.core.domain.useCase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteListMovie(movie: Movie, state: Boolean) = movieUseCase.setListFavoriteMovie(movie, state)

}