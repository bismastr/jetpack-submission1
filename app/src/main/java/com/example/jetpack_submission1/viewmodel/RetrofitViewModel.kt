package com.example.jetpack_submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.api.ApiConfig
import com.example.jetpack_submission1.model.DiscoverTvResponse
import com.example.jetpack_submission1.model.Response
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.model.TvResultsItem
import retrofit2.Call
import retrofit2.Callback

class RetrofitViewModel: ViewModel() {
    private val _response = MutableLiveData<Response>()
    val response: LiveData<Response> = _response

    private val _listMovieResult = MutableLiveData<List<MovieResultsItem>>()
    val listMovieResult: LiveData<List<MovieResultsItem>> = _listMovieResult

    private val _listTvResult = MutableLiveData<List<TvResultsItem>>()
    val listTvResult: LiveData<List<TvResultsItem>> = _listTvResult

    init {
        getMovie()
        getTv()
    }

    private fun getMovie(){
        val client = ApiConfig.getApiServices().getDiscover()
        client.enqueue(object :Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
               if (response.isSuccessful){
                   _listMovieResult.value = response.body()?.results
               }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getTv(){
        val client = ApiConfig.getApiServices().getTvDiscover()
        client.enqueue(object : Callback<DiscoverTvResponse>{
            override fun onResponse(
                call: Call<DiscoverTvResponse>,
                response: retrofit2.Response<DiscoverTvResponse>
            ) {
                if (response.isSuccessful){
                    _listTvResult.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<DiscoverTvResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}