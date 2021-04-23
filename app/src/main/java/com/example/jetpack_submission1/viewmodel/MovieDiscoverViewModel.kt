package com.example.jetpack_submission1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.model.Movie
import com.example.jetpack_submission1.utils.IdlingResources
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MovieDiscoverViewModel: ViewModel() {

    val movieData = MutableLiveData<ArrayList<Movie>>()
    fun setData(){
        IdlingResources.increment()
        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/discover/movie/?api_key=423b6f0f60e161184f1ecddb00f45512"
        client.get(url, object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val listMovie = ArrayList<Movie>()

                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("results")

                    for(i in 0 until items.length()){
                        val data = items.getJSONObject(i)
                        val movie = Movie()
                        movie.id = data.getInt("id")
                        movie.title = data.getString("original_title")
                        movie.poster = data.getString("poster_path")
                        movie.rating = data.getDouble("vote_average")
                        listMovie.add(movie)
                    }
                    movieData.postValue(listMovie)
                    IdlingResources.decrement()
                } catch (e: Exception){
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }

        })

    }

    fun getData(): LiveData<ArrayList<Movie>>{
        return movieData
    }
}