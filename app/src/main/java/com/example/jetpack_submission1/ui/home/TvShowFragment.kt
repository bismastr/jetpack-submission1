package com.example.jetpack_submission1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpack_submission1.adapter.FilmAdapter
import com.example.jetpack_submission1.databinding.FragmentTvshowBinding
import com.example.jetpack_submission1.viewmodel.TvDiscoverViewModel

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!
    //viewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvDiscoverViewModel: TvDiscoverViewModel
    //adapter
    private lateinit var adapter: FilmAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        val view = binding.root
        //viewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        tvDiscoverViewModel = ViewModelProvider(this).get(TvDiscoverViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }
    //setupRecyclerView
    private fun setupRecyclerView(){
        adapter = FilmAdapter()
        adapter.notifyDataSetChanged()
        binding.rvTvDiscover.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvTvDiscover.adapter = adapter
        tvDiscoverViewModel.setData()
        getData()
    }
    //getData
    private fun getData(){
        tvDiscoverViewModel.getData().observe(viewLifecycleOwner, { MovieList ->
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