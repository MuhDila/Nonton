package com.muhdila.core.data.source.local.room

import androidx.room.*
import com.muhdila.core.data.source.local.entitiy.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getListMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getListFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListFavoriteMovie(movieEntities: List<MovieEntity>)

    @Update
    fun updateListFavoriteMovie(movieEntity: MovieEntity)

}