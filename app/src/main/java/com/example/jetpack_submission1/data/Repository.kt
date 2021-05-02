package com.example.jetpack_submission1.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.model.MovieResultsItem

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
                    Log.d("DATA 1", movieList.toString())
                }
                movieResult.postValue(movieList)
            }

        })

        return movieResult
    }
}