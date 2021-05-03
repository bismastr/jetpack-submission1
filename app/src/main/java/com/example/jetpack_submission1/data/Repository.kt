package com.example.jetpack_submission1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.data.remote.respone.DetailMovieResponse
import com.example.jetpack_submission1.data.remote.respone.DetailTvResponse
import com.example.jetpack_submission1.data.remote.respone.TrendingResultItems
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.model.TvResultsItem

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    FilmDataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteDataSource).apply { instance = this }
            }


    }
    //Discover
    override fun getMovieDiscover(): LiveData<List<MovieDiscoverEntity>> {
        val movieResult = MutableLiveData<List<MovieDiscoverEntity>>()
        remoteDataSource.getDiscoverMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(response: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieDiscoverEntity>()
                for (i in response) {
                    val movie = MovieDiscoverEntity(
                        i.id,
                        i.posterPath,
                        i.title,
                        i.voteAverage
                    )
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }

        })

        return movieResult
    }

    override fun getTvDiscover(): LiveData<List<MovieDiscoverEntity>> {
        val tvResult = MutableLiveData<List<MovieDiscoverEntity>>()
        remoteDataSource.getDiscoverTv(object : RemoteDataSource.LoadTvCallback{
            override fun onAllTvReceived(response: List<TvResultsItem>) {
                val tvList = ArrayList<MovieDiscoverEntity>()
                for(i in response){
                    val tv = MovieDiscoverEntity(
                        i.id,
                        i.posterPath,
                        i.originalName,
                        i.voteAverage
                    )
                    tvList.add(tv)
                }
                tvResult.postValue(tvList)
            }

        })
        return tvResult
    }

    override fun getTrending(mediaType: String): LiveData<List<MovieDiscoverEntity>> {
        val trendingResult = MutableLiveData<List<MovieDiscoverEntity>>()
        remoteDataSource.getTrending(object : RemoteDataSource.LoadTrendingCallback{
            override fun onAllTrendingReceived(response: List<TrendingResultItems>) {
                val tvList = ArrayList<MovieDiscoverEntity>()
                if (mediaType == "movie"){
                    for(i in response){
                        val trending = MovieDiscoverEntity(
                            i.id,
                            i.backdropPath,
                            i.originalTitle,
                            i.voteAverage
                        )
                        tvList.add(trending)
                    }
                    trendingResult.postValue(tvList)
                } else if (mediaType == "tv"){
                    for(i in response){
                        val trending = MovieDiscoverEntity(
                            i.id,
                            i.backdropPath,
                            i.originalName,
                            i.voteAverage
                        )
                        tvList.add(trending)
                    }
                    trendingResult.postValue(tvList)
                }


            }


        }, mediaType)
        return trendingResult
    }

    override fun getMovieDetail(movieId: String): LiveData<MovieDetailEntity> {
        val detailResult = MutableLiveData<MovieDetailEntity>()
        remoteDataSource.getDetailMovie(object : RemoteDataSource.LoadDetailCallback{
            override fun onAllDetailReceived(response: DetailMovieResponse?) {
                val detail = MovieDetailEntity()
                if (response !== null){
                    detail.id = response.id
                    detail.overview = response.overview
                    detail.poster = response.posterPath
                    detail.rating = response.voteAverage
                    detail.release_date = response.releaseDate
                    detail.title = response.originalTitle
                }
                detailResult.postValue(detail)
            }
        }, movieId)
        return detailResult
    }

    override fun getTvDetail(tvId: String): LiveData<TvDetailEntity> {
        val detailResult = MutableLiveData<TvDetailEntity>()
        remoteDataSource.getDetailTv(object : RemoteDataSource.LoadDetailTvCallback {
            override fun onAllDetailTvReceived(response: DetailTvResponse?) {
                val detail = TvDetailEntity()
                if (response !== null){
                    detail.id = response.id
                    detail.numberEpisdoe = response.numberOfEpisodes
                    detail.numberSeasons = response.numberOfSeasons
                    detail.overview = response.overview
                    detail.poster = response.posterPath
                    detail.rating = response.voteAverage
                    detail.title = response.originalName
                    Log.d("TAG GET", detail.overview!!)
                }
                detailResult.postValue(detail)

            }

        }, tvId)
        return detailResult
    }


}