package com.example.jetpack_submission1.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.databinding.ActivityDetailTvBinding
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.ViewModelFactory

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private lateinit var tvViewModel: DetailViewModel

    private lateinit var tvId: String
    private lateinit var binding: ActivityDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ViewModel
        val factory = ViewModelFactory.getInstance(this)
        tvViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        //DataIntent
        getIntentData()
        getData()
    }

    private fun setData(detail: TvDetailEntity) {
        IdlingResources.increment()
        binding.tvOverviewTv.text = detail.overview
        binding.tvTitleTv.text = detail.title
        binding.tvEpisode.text = detail.numberEpisdoe.toString()
        binding.tvSeasons.text = detail.numberSeasons.toString()
        binding.tvRatingTv.text = detail.rating.toString()
        binding.ratingbarTv.rating = (detail.rating / 2).toFloat()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + detail.poster)
            .into(binding.imgPosterTv)
        showDetailLoading(false)
        IdlingResources.decrement()
    }

    private fun getData() {
        IdlingResources.increment()
        showDetailLoading(true)
        tvViewModel.getTvDetail(tvId).observe(this, { DetailData ->
            if (DetailData != null) {
                setData(DetailData)
            }
        })
        IdlingResources.decrement()
    }

    private fun getIntentData() {
        val dataIntent =
            intent.getParcelableExtra<MovieDiscoverEntity>(EXTRA_FILM) as MovieDiscoverEntity
        tvId = dataIntent.id.toString()
        Log.d("TAG", tvId)
    }

    private fun showDetailLoading(state: Boolean) {
        if (state) {
            binding.shimmerDetail.startShimmer()
            binding.shimmerDetail.visibility = View.VISIBLE
            binding.layoutDetail.visibility = View.GONE

        } else {
            binding.shimmerDetail.stopShimmer()
            binding.shimmerDetail.visibility = View.GONE
            binding.layoutDetail.visibility = View.VISIBLE
        }
    }
}