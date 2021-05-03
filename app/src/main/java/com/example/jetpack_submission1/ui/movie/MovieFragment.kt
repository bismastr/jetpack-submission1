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
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.databinding.FragmentMovieBinding
import com.example.jetpack_submission1.ui.detail.DetailActivity
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    //viewmodel
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
            override fun onItemClicked(data: MovieDiscoverEntity) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                startActivity(intentDetailActivity)
            }

        })
        adapterTrending.setOnItemCLickCallback(object : TrendingAdapter.OnItemClickCallback {
            override fun onItemClick(data: MovieDiscoverEntity) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                startActivity(intentDetailActivity)
            }

        })
    }

    //getData
    private fun getData(){
        IdlingResources.increment()
        movieViewModel.getMovieDiscover().observe(viewLifecycleOwner, {MovieList ->
            if(MovieList !== null){
                val movieArray = MovieList as ArrayList<MovieDiscoverEntity>
                adapterDiscover.setData(movieArray)
                Log.d("DATA", MovieList.toString())
            }
        })
        IdlingResources.decrement()
    }

    //getDataTrending
    private fun getDataTrending() {
        IdlingResources.increment()
        movieViewModel.getMovieTrending().observe(viewLifecycleOwner, { TrendingList ->
            if (TrendingList !== null) {
                val trendingArray  = TrendingList as ArrayList<MovieDiscoverEntity>
                adapterTrending.setData(trendingArray)
            }
        })
        IdlingResources.decrement()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}