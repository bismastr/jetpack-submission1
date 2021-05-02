package com.example.jetpack_submission1.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.api.ApiConfig
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.model.Response
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

    fun getDiscoverMovie(): MutableLiveData<List<MovieResultsItem>> {
        val liveData = MutableLiveData<List<MovieResultsItem>>()
        val client = ApiConfig.getApiServices().getDiscover()
        client.enqueue(object : Callback<Response>{
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                liveData.value = response.body()?.results
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return liveData
    }

}