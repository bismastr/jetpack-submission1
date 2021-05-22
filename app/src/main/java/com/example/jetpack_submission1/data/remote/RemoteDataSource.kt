package com.example.jetpack_submission1.data.remote

import android.util.Log
import com.example.jetpack_submission1.api.ApiConfig
import com.example.jetpack_submission1.api.ApiResponse
import com.example.jetpack_submission1.data.remote.respone.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    suspend fun getDiscoverMovie(): Flow<ApiResponse<List<MovieResultsItem>>> {
        return flow {
            try {
                val response = ApiConfig.getApiServices().getDiscover()
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
                val response = ApiConfig.getApiServices().getTrending(mediaType)
                val dataArray = response.results as ArrayList<TrendingResultItems>
                if (dataArray.isNotEmpty()){
                    this.emit(ApiResponse.Success(response.results))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDiscoverTv(): Flow<ApiResponse<List<TvResultsItem>>> {
       return flow {
           try {
               val response = ApiConfig.getApiServices().getTvDiscover()
               val dataArray = response.results as ArrayList<TvResultsItem>
               if(dataArray.isNotEmpty()){
                   this.emit(ApiResponse.Success(response.results))
               } else {
                   this.emit(ApiResponse.Empty)
               }
           }catch (e: Exception) {
               this.emit(ApiResponse.Error(e.toString()))
           }
       }.flowOn(Dispatchers.IO)
    }



    suspend fun getDetailMovie(movieId: String): Flow<ApiResponse<DetailMovieResponse>> {
        return flow {
            try {
                val response = ApiConfig.getApiServices().getMovieDetail(movieId)
                if (response !== null){
                    this.emit(ApiResponse.Success(response))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTv(tvId: String): Flow<ApiResponse<DetailTvResponse>> {
        return flow {
            try {
                val response = ApiConfig.getApiServices().getTvDetail(tvId)
                if (response !== null){
                    this.emit(ApiResponse.Success(response))
                } else {
                    this.emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                this.emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


    interface LoadMovieCallback {
        suspend fun onAllMovieReceived(response: Flow<ApiResponse<List<MovieResultsItem>>>)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(response: List<TvResultsItem>)
    }

    interface LoadTrendingCallback {
        fun onAllTrendingReceived(response: List<TrendingResultItems>)
    }

    interface LoadDetailCallback {
        fun onAllDetailReceived(response: DetailMovieResponse?)
    }

    interface LoadDetailTvCallback {
        fun onAllDetailTvReceived(response: DetailTvResponse?)
    }

}