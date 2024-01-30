package com.muhdila.nonton.di

import com.muhdila.core.domain.useCase.MovieInteractor
import com.muhdila.core.domain.useCase.MovieUseCase
import com.muhdila.nonton.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}