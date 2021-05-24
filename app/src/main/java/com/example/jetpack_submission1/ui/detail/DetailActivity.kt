package com.example.jetpack_submission1.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.brillante.core.data.Resource
import com.brillante.core.data.local.entity.FavoriteEntity
import com.brillante.core.domain.model.MovieDetail
import com.brillante.core.domain.model.MovieDiscover
import com.brillante.core.domain.model.TvDetail
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_FROM = "extra_from"
        const val EXTRA_FAVO = "extra_favo"
    }

    //from tv or movie
    private var from: Int = 0

    private val tvViewModel: DetailViewModel by viewModel()

    private lateinit var id: String
    private lateinit var binding: ActivityDetailBinding

//    //favorite
//    private lateinit var favoriteEntity: FavoriteEntity
//    private var _isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ViewModel
//        val factory = ViewModelFactory.getInstance(this)
//        tvViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val isFromFavo = intent.getBooleanExtra(EXTRA_FAVO, false)

        if (isFromFavo) {
            getIntentDataFavo()
        } else {
            getIntentData()
        }

        if (from == 1) {
            getData()
        } else {
            getMovieData(id)
        }


    }

    private fun getIntentDataFavo() {
        val dataIntent =
            intent.getParcelableExtra<FavoriteEntity>(EXTRA_FILM) as FavoriteEntity
        from = intent.getIntExtra(EXTRA_FROM, 0)
        id = dataIntent.id.toString()
    }

    private fun setFavorite(state: Boolean) {
        binding.btnFavorite.isChecked = state
    }

    //MovieDetail
    private fun setMovieData(detail: MovieDetail) {

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

        var stateFavorite = detail.isFavorite
        Log.d("STATEFAVO", stateFavorite.toString())
        setFavorite(stateFavorite)
        binding.btnFavorite.setOnClickListener {
            stateFavorite = !stateFavorite
            tvViewModel.setMovieFavorite(detail, stateFavorite)
            Log.d("STATEFAVO", stateFavorite.toString())
        }

    }

    private fun getMovieData(movieId: String) {
        tvViewModel.getMovieDetail(movieId).observe(this, { Detail ->
            if (Detail != null) {
                when (Detail) {
                    is Resource.Loading -> showDetailLoading(true)
                    is Resource.Success -> {
                        showDetailLoading(false)
                        Detail.data?.let { setMovieData(it) }
                    }
                    is Resource.Error -> Log.d("TAG", "GetMovieDetailError")
                }
            }
        })

    }

    //TvDetail
    private fun setData(detail: TvDetail) {
        binding.tvOverviewTv.text = detail.overview
        binding.tvTitleTv.text = detail.title
        binding.tvEpisode.text = detail.numberEpisode.toString()
        binding.tvSeasons.text = detail.numberSeasons.toString()
        binding.tvRatingTv.text = detail.rating.toString()
        binding.ratingbarTv.rating = (detail.rating / 2).toFloat()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + detail.poster)
            .into(binding.imgPosterTv)
        showDetailLoading(false)

        var stateFavorite = detail.isFavorite
        Log.d("STATEFAVO", stateFavorite.toString())
        setFavorite(stateFavorite)
        binding.btnFavorite.setOnClickListener {
            stateFavorite = !stateFavorite
            tvViewModel.setTvFavorite(detail, stateFavorite)
            setFavorite(stateFavorite)
            Log.d("STATEFAVO", stateFavorite.toString())
        }

    }

    private fun getData() {
        showDetailLoading(true)
        tvViewModel.getTvDetail(id).observe(this) { DetailData ->
            if (DetailData != null) {
                when (DetailData) {
                    is Resource.Loading -> showDetailLoading(true)
                    is Resource.Success -> {
                        showDetailLoading(false)
                        DetailData.data?.let { setData(it) }
                    }
                    is Resource.Error -> Log.d("TAG", "GetMovieDetailError")
                }
            }
        }

    }

    private fun getIntentData() {
        val dataIntent =
            intent.getParcelableExtra<MovieDiscover>(EXTRA_FILM) as MovieDiscover
        from = intent.getIntExtra(EXTRA_FROM, 0)
        id = dataIntent.id.toString()
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