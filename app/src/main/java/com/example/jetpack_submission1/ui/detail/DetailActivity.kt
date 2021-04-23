package com.example.jetpack_submission1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.R
import com.example.jetpack_submission1.databinding.ActivityDetailBinding
import com.example.jetpack_submission1.model.Detail
import com.example.jetpack_submission1.model.Movie
import com.example.jetpack_submission1.viewmodel.MovieDetailViewModel

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_FILM = "extra_film"
    }
    private val detailData = ArrayList<Detail>()
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        val dataIntent = intent.getParcelableExtra<Movie>(EXTRA_FILM) as Movie
        val movieId = dataIntent.id.toString()
        movieDetailViewModel.setData(movieId)
        getData()
    }
    private fun setData(detail: Detail ){
        binding.tvOverview.text = detail.overview
        binding.tvTitle.text = detail.title
        binding.tvRating.text = detail.rating.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+detail.poster)
            .into(binding.imgPoster)
    }

    private fun getData(){
        movieDetailViewModel.getData().observe(this,{DetailData ->
            if(DetailData != null){
                setData(DetailData)
            }
        })
    }
}