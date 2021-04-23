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

class TvTrendingViewModel : ViewModel() {
    val tvData = MutableLiveData<ArrayList<Movie>>()

    fun setData() {

        val client = AsyncHttpClient()
        val url =
            "https://api.themoviedb.org/3/trending/tv/week?api_key=423b6f0f60e161184f1ecddb00f45512"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    IdlingResources.increment()
                    val listMovie = ArrayList<Movie>()
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("results")

                    for (i in 0 until items.length()) {
                        val data = items.getJSONObject(i)
                        val tv = Movie()
                        tv.id = data.getInt("id")
                        tv.title = data.getString("name")
                        tv.poster = data.getString("backdrop_path")
                        tv.rating = data.getDouble("vote_average")
                        listMovie.add(tv)
                    }
                    tvData.postValue(listMovie)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
                IdlingResources.decrement()
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

    fun getData(): LiveData<ArrayList<Movie>> {
        return tvData
    }
}