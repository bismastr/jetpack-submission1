package com.brillante.core.api



import com.brillante.core.BuildConfig
import com.brillante.core.data.remote.respone.*
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
    suspend fun getMovieDetail(@Path("movie_id") movieId: String): DetailMovieResponse?

    @GET("tv/{tv_id}?api_key=${KEY}")
    suspend fun getTvDetail(@Path("tv_id") tvId: String): DetailTvResponse?

}