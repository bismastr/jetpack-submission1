package com.example.jetpack_submission1.api


import com.example.jetpack_submission1.BuildConfig
import com.example.jetpack_submission1.data.remote.respone.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val KEY = BuildConfig.API_KEY
    }

    @GET("discover/movie?api_key=${KEY}")
    suspend fun getDiscover(): Response

    @GET("discover/tv?api_key=${KEY}")
    suspend fun getTvDiscover(): DiscoverTvResponse

    @GET("trending/{media_type}/week?api_key=${KEY}")
    suspend fun getTrending(@Path("media_type") mediaType: String): TrendingResponse

    @GET("movie/{movie_id}?api_key=${KEY}")
    fun getMovieDetail(@Path("movie_id") movieId: String): Call<DetailMovieResponse>

    @GET("tv/{tv_id}?api_key=${KEY}")
    fun getTvDetail(@Path("tv_id") tvId: String): Call<DetailTvResponse>

}