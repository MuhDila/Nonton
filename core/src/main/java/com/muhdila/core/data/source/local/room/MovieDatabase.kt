package com.muhdila.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhdila.core.data.source.local.entitiy.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}