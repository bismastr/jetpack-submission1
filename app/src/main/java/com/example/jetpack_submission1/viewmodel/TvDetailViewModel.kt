package com.example.jetpack_submission1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_submission1.model.DetailTrending
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class TvDetailViewModel : ViewModel() {

    val detailData = MutableLiveData<DetailTrending>()

    fun setData(movieId: String) {
        val client = AsyncHttpClient()
        val url =
            "https://api.themoviedb.org/3/tv/${movieId}?api_key=423b6f0f60e161184f1ecddb00f45512&language=en-US"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {

                    val result = String(responseBody)
                    val data = JSONObject(result)

                    val detail = DetailTrending()
                    detail.id = data.getInt("id")
                    detail.title = data.getString("name")
                    detail.numberEpisdoe = data.getInt("number_of_episodes")
                    detail.numberSeasons = data.getInt("number_of_seasons")
                    detail.overview = data.getString("overview")
                    detail.poster = data.getString("poster_path")
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

    fun getData(): LiveData<DetailTrending> {
        return detailData
    }
}