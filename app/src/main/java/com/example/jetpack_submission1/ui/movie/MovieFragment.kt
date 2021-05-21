package com.example.jetpack_submission1.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpack_submission1.adapter.FilmAdapter
import com.example.jetpack_submission1.adapter.TrendingAdapter
import com.example.jetpack_submission1.data.Resource
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.databinding.FragmentMovieBinding
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.ui.detail.DetailActivity
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding as FragmentMovieBinding

    //viewModel
    private lateinit var movieViewModel: MovieViewModel

    //adapter
    private lateinit var adapterDiscover: FilmAdapter
    private lateinit var adapterTrending: TrendingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        //Viewmodel
        val factory = ViewModelFactory.getInstance(requireActivity())
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        return view
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
//            override fun onItemClicked(data: MovieDiscoverEntity) {
//                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
//                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
//                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 0)
//                startActivity(intentDetailActivity)
//            }

            override fun onItemClicked(data: MovieDiscover) {
                TODO("Not yet implemented")
            }

        })
        adapterTrending.setOnItemCLickCallback(object : TrendingAdapter.OnItemClickCallback {
            override fun onItemClick(data: MovieDiscoverEntity) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 0)
                startActivity(intentDetailActivity)
            }

        })
    }

    //getData
    private fun getData() {
        IdlingResources.increment()
        showDiscoverLoading(true)
        movieViewModel.movieDiscover.observe(viewLifecycleOwner, { MovieList ->
            if (MovieList != null){
                when(MovieList){
                    is Resource.Loading -> showDiscoverLoading(true)
                    is Resource.Success -> {
                        showDiscoverLoading(false)
                        val movieArrayList = MovieList.data as ArrayList<MovieDiscover>
                        adapterDiscover.setData(movieArrayList)
                    }
                    is Resource.Error -> Log.d("TAG", "Get data discover error")
                }
            }
        })

        IdlingResources.decrement()
    }

    //getDataTrending
    private fun getDataTrending() {
        IdlingResources.increment()
        showTrendingLoading(true)
        movieViewModel.getMovieTrending().observe(viewLifecycleOwner, { TrendingList ->
            if (TrendingList !== null) {
                val trendingArray = TrendingList as ArrayList<MovieDiscoverEntity>
                showTrendingLoading(false)
                adapterTrending.setData(trendingArray)
            }

        })
        IdlingResources.decrement()

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