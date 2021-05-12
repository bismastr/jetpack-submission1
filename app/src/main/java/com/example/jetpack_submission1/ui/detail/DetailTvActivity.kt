package com.example.jetpack_submission1.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.data.local.entity.MovieDetailEntity
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.data.local.entity.TvDetailEntity
import com.example.jetpack_submission1.databinding.ActivityDetailTvBinding
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_FROM = "extra_from"
    }
    //from tv or movie
    private var from: Int = 0

    private lateinit var tvViewModel: DetailViewModel

    private lateinit var id: String
    private lateinit var binding: ActivityDetailTvBinding
    //favorite
    private lateinit var favoriteEntity: FavoriteEntity
    private var _isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ViewModel
        val factory = ViewModelFactory.getInstance(this)
        tvViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        getIntentData()
        if (from == 1) {
            getData()
        } else {
            getMovieData()
        }

    }

    private fun favoriteButton(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _isChecked = tvViewModel.isChecked(id)
            Log.d("TAG", _isChecked.toString())
            withContext(Dispatchers.Main) {
                binding.btnFavorite.isChecked = _isChecked
            }
        }

        binding.btnFavorite.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
                tvViewModel.insert(favoriteEntity)
            } else {
                tvViewModel.delete(favoriteEntity)
            }
            binding.btnFavorite.isChecked = _isChecked
        }
    }

    //MovieDetail
    private fun setMovieData(detail: MovieDetailEntity) {
        IdlingResources.increment()
        binding.tvOverviewTv.text = detail.overview
        binding.tvTitleTv.text = detail.title
        binding.tvRatingTv.text = detail.rating.toString()
        binding.ratingbarTv.rating = (detail.rating / 2).toFloat()
        binding.cvEpisode.visibility = View.GONE
        binding.cvSeason.visibility = View.GONE
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + detail.poster)
            .into(binding.imgPosterTv)
        showDetailLoading(false)
        IdlingResources.decrement()

        favoriteEntity = FavoriteEntity(
            id = detail.id,
            poster = detail.poster,
            title = detail.title,
            rating = detail.rating,
            from = from,
        )

        favoriteButton(detail.id)
    }

    private fun getMovieData() {
        showDetailLoading(true)
        tvViewModel.getMovieDetail(id).observe(this, { DetailData ->
            if (DetailData != null) {
                setMovieData(DetailData)
            }
        })

    }

    //TvDetail
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

        favoriteEntity = FavoriteEntity(
            id = detail.id,
            poster = detail.poster,
            title = detail.title,
            rating = detail.rating,
            from = from,
        )

        favoriteButton(detail.id)
    }

    private fun getData() {
        showDetailLoading(true)
        tvViewModel.getTvDetail(id).observe(this) { DetailData ->
            if (DetailData != null) {
                setData(DetailData)
            }
        }

    }

    private fun getIntentData() {
        val dataIntent =
            intent.getParcelableExtra<MovieDiscoverEntity>(EXTRA_FILM) as MovieDiscoverEntity
        from = intent.getIntExtra(EXTRA_FROM, 0)
        id = dataIntent.id.toString()
        Log.d("TAG", id)
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