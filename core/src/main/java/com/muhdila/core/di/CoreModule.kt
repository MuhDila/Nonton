package com.muhdila.core.di

import androidx.room.Room
import com.muhdila.core.BuildConfig
import com.muhdila.core.domain.repository.IMovieRepository
import com.muhdila.core.data.source.local.room.MovieDatabase
import com.muhdila.core.data.source.remote.RemoteDataSource
import com.muhdila.core.data.source.remote.network.ApiService
import com.muhdila.core.utils.AppExecutors
import com.muhdila.core.data.source.MovieRepository
import com.muhdila.core.data.source.local.LocalDataSource
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "UserGithub.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()

                val modifiedRequest = originalRequest.newBuilder()
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
                    .build()

                chain.proceed(modifiedRequest)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}