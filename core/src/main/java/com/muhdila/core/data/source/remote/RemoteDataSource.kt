package com.muhdila.core.data.source.remote

import android.util.Log
import com.muhdila.core.data.source.remote.network.ApiResponse
import com.muhdila.core.data.source.remote.network.ApiService
import com.muhdila.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getListMovie()
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: return@flow){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        const val TAG = "Remote Data Source"
    }

}