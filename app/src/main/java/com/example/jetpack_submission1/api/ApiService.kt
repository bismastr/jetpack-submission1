package com.example.jetpack_submission1.api


import com.example.jetpack_submission1.model.DiscoverTvResponse
import com.example.jetpack_submission1.model.Response
import com.example.jetpack_submission1.model.MovieResultsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val KEY = "423b6f0f60e161184f1ecddb00f45512"
    }

    @GET("discover/movie?api_key=${KEY}")
    fun getDiscover(): Call<Response>

    @GET("discover/tv?api_key=${KEY}")
    fun getTvDiscover(): Call<DiscoverTvResponse>
}