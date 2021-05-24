package com.example.jetpack_submission1.data.remote

import android.util.Log
import com.example.jetpack_submission1.api.ApiResponse
import com.example.jetpack_submission1.api.ApiService
import com.example.jetpack_submission1.data.remote.respone.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getDiscoverMovie(): Flow<ApiResponse<List<MovieResultsItem>>> {
        return flow {
            try {
                val response = apiService.getDiscover()
                val dataArray = response.results as ArrayList<MovieResultsItem>
                if (dataArray.isNotEmpty()) {
                    this.emit(ApiResponse.Success(response.results))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                this.emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrending(mediaType: String): Flow<ApiResponse<List<TrendingResultItems>>> {

        return flow {
            try {
                val response = apiService.getTrending(mediaType)
                val dataArray = response.results as ArrayList<TrendingResultItems>
                if (dataArray.isNotEmpty()) {
                    this.emit(ApiResponse.Success(response.results))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDiscoverTv(): Flow<ApiResponse<List<TvResultsItem>>> {
        return flow {
            try {
                val response = apiService.getTvDiscover()
                val dataArray = response.results as ArrayList<TvResultsItem>
                if (dataArray.isNotEmpty()) {
                    this.emit(ApiResponse.Success(response.results))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getDetailMovie(movieId: String): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movieId)
                if (response !== null) {
                    this.emit(ApiResponse.Success(response))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTv(tvId: String): Flow<ApiResponse<DetailTvResponse>> {
        return flow {
            try {
                val response = apiService.getTvDetail(tvId)
                if (response !== null) {
                    this.emit(ApiResponse.Success(response))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}