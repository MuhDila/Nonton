package com.muhdila.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muhdila.core.domain.useCase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val getListFavoriteMovie = movieUseCase.getListFavoriteMovie().asLiveData()

}