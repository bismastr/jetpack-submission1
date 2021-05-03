package com.example.jetpack_submission1.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.databinding.ActivityDetailBinding
import com.example.jetpack_submission1.viewmodel.MovieDetailViewModel
import com.example.jetpack_submission1.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var movieId: String
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var binding: ActivityDetailBinding
    //New ViewModel
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        //ViewModel
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        getIntentData()
        getData()
    }

    private fun setData(detail: MovieDetailEntity) {
        binding.tvOverviewMovie.text = detail.overview
        binding.tvTitleMovie.text = detail.title
        binding.tvRatingMovie.text = detail.rating.toString()
        binding.ratingbarMovie.rating = (detail.rating / 2).toFloat()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + detail.poster)
            .into(binding.imgPosterMovie)
    }

    private fun getData() {
        detailViewModel.getMovieDetail(movieId).observe(this, { DetailData ->
            if (DetailData != null) {
                setData(DetailData)
            }
        })
    }

    private fun getIntentData(){
        val dataIntent = intent.getParcelableExtra<MovieDiscoverEntity>(EXTRA_FILM) as MovieDiscoverEntity
        movieId = dataIntent.id.toString()
        Log.d("ID", movieId)
    }
}