package com.muhdila.nonton.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muhdila.core.domain.useCase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    val getListMovie = movieUseCase.getListMovie().asLiveData()

    // TODO Search
    fun searchMovieResult(query: String) = movieUseCase.searchMovies(query).asLiveData()

}