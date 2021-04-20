package com.example.jetpack_submission1.ui.movie

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_submission1.adapter.MovieListAdapter
import com.example.jetpack_submission1.databinding.FragmentMovieBinding
import com.example.jetpack_submission1.model.MovieList
import com.example.jetpack_submission1.viewmodel.MovieDiscoverViewModel

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    //viewmodel
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var movieListViewModel: MovieDiscoverViewModel
    //adapter
    private lateinit var adapter: MovieListAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        movieListViewModel = ViewModelProvider(this).get(MovieDiscoverViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        adapter = MovieListAdapter()
        adapter.notifyDataSetChanged()

        binding.rvMovieDiscover.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvMovieDiscover.adapter = adapter

        getData()
    }

    private fun getData(){
        movieListViewModel.setData()
        movieListViewModel.getUser().observe(viewLifecycleOwner, {MovieList ->
            if (MovieList !== null){
                adapter.setData(MovieList)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}