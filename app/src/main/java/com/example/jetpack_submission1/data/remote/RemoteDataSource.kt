package com.example.jetpack_submission1.data.remote

import android.util.Log
import com.example.jetpack_submission1.api.ApiConfig
import com.example.jetpack_submission1.api.ApiResponse
import com.example.jetpack_submission1.data.remote.respone.*
import com.example.jetpack_submission1.utils.IdlingResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception

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

    fun getTrending(callback: LoadTrendingCallback, mediaType: String) {
        var listData: List<TrendingResultItems>
        val client = ApiConfig.getApiServices().getTrending(mediaType)
        client.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: retrofit2.Response<TrendingResponse>
            ) {
                listData = response.body()?.results as ArrayList<TrendingResultItems>
                callback.onAllTrendingReceived(listData)

            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    fun getDiscoverTv(callback: LoadTvCallback) {
        var listData: ArrayList<TvResultsItem>
        val client = ApiConfig.getApiServices().getTvDiscover()
        client.enqueue(object : Callback<DiscoverTvResponse> {
            override fun onResponse(
                call: Call<DiscoverTvResponse>,
                response: retrofit2.Response<DiscoverTvResponse>
            ) {
                listData = response.body()?.results as ArrayList<TvResultsItem>
                callback.onAllTvReceived(listData)
            }

            override fun onFailure(call: Call<DiscoverTvResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }



    fun getDetailMovie(callback: LoadDetailCallback, movieId: String) {
        IdlingResources.increment()
        var listData: DetailMovieResponse?
        val client = ApiConfig.getApiServices().getMovieDetail(movieId)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: retrofit2.Response<DetailMovieResponse>
            ) {
                listData = response.body()
                callback.onAllDetailReceived(listData)
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
        IdlingResources.decrement()
    }

    fun getDetailTv(callback: LoadDetailTvCallback, tvId: String) {
        var listData: DetailTvResponse?
        val client = ApiConfig.getApiServices().getTvDetail(tvId)
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: retrofit2.Response<DetailTvResponse>
            ) {
                listData = response.body()
                Log.d("TEST", listData.toString())
                callback.onAllDetailTvReceived(listData)
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.d("Error", t.toString())
            }

        })
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