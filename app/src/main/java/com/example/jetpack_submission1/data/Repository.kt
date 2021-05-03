package com.example.jetpack_submission1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.remote.RemoteDataSource
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
}