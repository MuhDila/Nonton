package com.muhdila.core.data.source

import com.muhdila.core.data.source.local.LocalDataSource
import com.muhdila.core.data.source.remote.RemoteDataSource
import com.muhdila.core.data.source.remote.network.ApiResponse
import com.muhdila.core.data.source.remote.response.MovieResponse
import com.muhdila.core.domain.model.Movie
import com.muhdila.core.domain.repository.IMovieRepository
import com.muhdila.core.utils.AppExecutors
import com.muhdila.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getListMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getListMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val userGithubList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertListFavoriteMovie(userGithubList)
            }
        }.asFlow()

    override fun getListFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getListFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setListFavoriteMovie(movie: Movie, state: Boolean) {
        val userGithubEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setListFavoriteMovie(userGithubEntity, state)
        }
    }

    // TODO Search
    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.searchMovie(query).first()) {
            is ApiResponse.Success -> {
                val response = DataMapper.mapResponseToEntities(apiResponse.data)
                emit(Resource.Success(DataMapper.mapEntitiesToDomain(response)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("No result found, please try different keyword"))
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error(
                        apiResponse.errorMessage
                    )
                )
            }
        }
    }

}