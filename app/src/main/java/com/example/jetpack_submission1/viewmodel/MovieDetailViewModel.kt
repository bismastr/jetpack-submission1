package com.example.jetpack_submission1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.model.Detail
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MovieDetailViewModel : ViewModel() {

    val detailData = MutableLiveData<Detail>()

    fun setData(movieId: String) {
        val client = AsyncHttpClient()
        val url =
            "https://api.themoviedb.org/3/movie/${movieId}?api_key=423b6f0f60e161184f1ecddb00f45512&language=en-US"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {

                    val result = String(responseBody)
                    val data = JSONObject(result)

                    val detail = Detail()
                    detail.id = data.getInt("id")
                    detail.title = data.getString("original_title")
                    detail.overview = data.getString("overview")
                    detail.poster = data.getString("poster_path")
                    detail.release_date = data.getString("release_date")
                    detail.rating = data.getDouble("vote_average")

                    detailData.postValue(detail)
                } catch (e: Exception) {
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

    fun getData(): LiveData<Detail> {
        return detailData
    }
}