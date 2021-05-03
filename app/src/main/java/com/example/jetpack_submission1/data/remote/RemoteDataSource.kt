package com.example.jetpack_submission1.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.api.ApiConfig
import com.example.jetpack_submission1.model.DiscoverTvResponse
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.model.Response
import com.example.jetpack_submission1.model.TvResultsItem
import retrofit2.Call
import retrofit2.Callback

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getDiscoverMovie(callback:LoadMovieCallback) {
        var liveData: ArrayList<MovieResultsItem>
        val client = ApiConfig.getApiServices().getDiscover()
        client.enqueue(object : Callback<Response>{
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                liveData = (response.body()?.results as ArrayList<MovieResultsItem>?)!!
                callback.onAllMovieReceived(liveData)
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
       
    }

    fun getDiscoverTv(callback: LoadTvCallback){
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

    interface LoadMovieCallback {
        fun onAllMovieReceived(response: List<MovieResultsItem>)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(response: List<TvResultsItem>)
    }

}