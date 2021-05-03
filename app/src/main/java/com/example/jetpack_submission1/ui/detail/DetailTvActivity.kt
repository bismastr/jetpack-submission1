package com.example.jetpack_submission1.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.databinding.ActivityDetailTvBinding
import com.example.jetpack_submission1.model.DetailTrending
import com.example.jetpack_submission1.model.Movie
import com.example.jetpack_submission1.model.TvResultsItem
import com.example.jetpack_submission1.viewmodel.TvDetailViewModel

class DetailTvActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_FROM = 0
    }

    private lateinit var tvDetailViewModel: TvDetailViewModel
    private lateinit var tvId: String
    private lateinit var binding: ActivityDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvDetailViewModel = ViewModelProvider(this).get(TvDetailViewModel::class.java)
        val dataIntent = intent.getParcelableExtra<MovieDiscoverEntity>(EXTRA_FILM) as MovieDiscoverEntity
        tvId = dataIntent.id.toString()
        getData()
    }

    private fun setData(detail: DetailTrending) {
        binding.tvOverviewTv.text = detail.overview
        binding.tvTitleTv.text = detail.title
        binding.tvEpisode.text = detail.numberEpisdoe.toString()
        binding.tvSeasons.text = detail.numberSeasons.toString()
        binding.tvRatingTv.text = detail.rating.toString()
        binding.ratingbarTv.rating = (detail.rating / 2).toFloat()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + detail.poster)
            .into(binding.imgPosterTv)
    }

    private fun getData() {
        tvDetailViewModel.setData(tvId)
        tvDetailViewModel.getData().observe(this, { DetailData ->
            if (DetailData != null) {
                setData(DetailData)
            }
        })
    }
}