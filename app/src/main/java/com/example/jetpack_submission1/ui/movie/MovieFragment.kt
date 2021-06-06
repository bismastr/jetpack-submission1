package com.example.jetpack_submission1.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brillante.core.data.Resource
import com.brillante.core.domain.model.MovieDiscover
import com.example.jetpack_submission1.adapter.FilmAdapter
import com.example.jetpack_submission1.adapter.TrendingAdapter
import com.example.jetpack_submission1.databinding.FragmentMovieBinding
import com.example.jetpack_submission1.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding

    //viewModel
    private val movieViewModel: MovieViewModel by viewModel()

    //adapter
    private lateinit var adapterDiscover: FilmAdapter
    private lateinit var adapterTrending: TrendingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    //setupRecyclerView
    private fun setupRecyclerView() {
        adapterDiscover = FilmAdapter()
        adapterTrending = TrendingAdapter()
        adapterDiscover.notifyDataSetChanged()
        binding.rvMovieDiscover.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvTrendingMovie.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvMovieDiscover.adapter = adapterDiscover
        binding.rvTrendingMovie.adapter = adapterTrending
        getDataTrending()
        getData()
        onItemClick()
    }

    //onItemClick
    private fun onItemClick() {
        adapterDiscover.setOnItemCLickCallback(object : FilmAdapter.OnItemClickCallback {

            override fun onItemClicked(data: MovieDiscover) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 0)
                startActivity(intentDetailActivity)
            }

        })
        adapterTrending.setOnItemCLickCallback(object : TrendingAdapter.OnItemClickCallback {
            override fun onItemClick(data: MovieDiscover) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 0)
                startActivity(intentDetailActivity)
            }
        })
    }

    //getData
    private fun getData() {
        movieViewModel.movieDiscover.observe(viewLifecycleOwner, { MovieList ->
            if (MovieList != null) {
                when (MovieList) {
                    is Resource.Loading -> showDiscoverLoading(true)
                    is Resource.Success -> {
                        showDiscoverLoading(false)
                        val movieArrayList = MovieList.data as ArrayList<MovieDiscover>
                        adapterDiscover.setData(movieArrayList)
                    }
                    is Resource.Error -> {
                        binding.shimmerDiscoverMovie.visibility = View.GONE
                        binding.imgDiscoverError.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    //getDataTrending
    private fun getDataTrending() {
        movieViewModel.movieTrending.observe(viewLifecycleOwner, { TrendingList ->
            if (TrendingList != null) {
                when (TrendingList) {
                    is Resource.Loading -> showTrendingLoading(true)
                    is Resource.Success -> {
                        showTrendingLoading(false)
                        val trendingArrayList = TrendingList.data as ArrayList<MovieDiscover>
                        adapterTrending.setData(trendingArrayList)
                    }
                    is Resource.Error ->  {
                        binding.shimmerTrendingMovie.visibility = View.GONE
                        binding.imgTrendingError.visibility = View.VISIBLE
                    }
                }
            }
        })

    }

    //Loading
    private fun showTrendingLoading(state: Boolean) {
        if (state) {
            binding.shimmerTrendingMovie.startShimmer()
            binding.shimmerTrendingMovie.visibility = View.VISIBLE
            binding.rvTrendingMovie.visibility = View.GONE
        } else {
            binding.shimmerTrendingMovie.stopShimmer()
            binding.shimmerTrendingMovie.visibility = View.GONE
            binding.rvTrendingMovie.visibility = View.VISIBLE
        }
    }

    private fun showDiscoverLoading(state: Boolean) {
        if (state) {
            binding.shimmerDiscoverMovie.startShimmer()
            binding.shimmerDiscoverMovie.visibility = View.VISIBLE
            binding.rvMovieDiscover.visibility = View.GONE

        } else {
            binding.shimmerDiscoverMovie.stopShimmer()
            binding.shimmerDiscoverMovie.visibility = View.GONE
            binding.rvMovieDiscover.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}