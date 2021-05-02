package com.example.jetpack_submission1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.remote.RemoteDataSource
import com.example.jetpack_submission1.model.MovieResultsItem

class Repository private constructor(private val remoteDataSource: RemoteDataSource): FilmDataSource{

    companion object{
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): Repository =
            instance ?: synchronized(this){
                instance ?: Repository(remoteDataSource).apply { instance = this }
            }


    }

    override fun getMovieDiscover(): LiveData<List<MovieResultsItem>> {
        return remoteDataSource.getDiscoverMovie()
    }
}