package com.muhdila.core.data.source.local

import com.muhdila.core.data.source.local.entitiy.MovieEntity
import com.muhdila.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getListMovie(): Flow<List<MovieEntity>> = movieDao.getListMovie()

    fun getListFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getListFavoriteMovie()

    suspend fun insertListFavoriteMovie(movieEntityList: List<MovieEntity>) = movieDao.insertListFavoriteMovie(movieEntityList)

    fun setListFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.updateListFavoriteMovie(movieEntity)
    }

}