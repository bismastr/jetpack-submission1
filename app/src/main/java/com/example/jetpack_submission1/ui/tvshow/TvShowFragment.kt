package com.example.jetpack_submission1.ui.tvshow

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
import com.example.jetpack_submission1.adapter.TvDiscoverAdapter
import com.example.jetpack_submission1.databinding.FragmentTvshowBinding
import com.example.jetpack_submission1.model.Movie
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.model.TvResultsItem
import com.example.jetpack_submission1.ui.detail.DetailActivity
import com.example.jetpack_submission1.ui.detail.DetailTvActivity
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.RetrofitViewModel
import com.example.jetpack_submission1.viewmodel.TvDiscoverViewModel
import com.example.jetpack_submission1.viewmodel.TvTrendingViewModel

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!

    //viewModel
    private lateinit var retrofitViewModel: RetrofitViewModel
    private lateinit var tvDiscoverViewModel: TvDiscoverViewModel
    private lateinit var tvTrendingViewModel: TvTrendingViewModel

    //adapter
    private lateinit var adapterDiscover: TvDiscoverAdapter
    private lateinit var adapterTrending: TrendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        val view = binding.root
        //viewModel
        tvDiscoverViewModel = ViewModelProvider(this).get(TvDiscoverViewModel::class.java)
        tvTrendingViewModel = ViewModelProvider(this).get(TvTrendingViewModel::class.java)
        retrofitViewModel = ViewModelProvider(this).get(RetrofitViewModel::class.java)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    //setupRecyclerView
    private fun setupRecyclerView() {
        adapterDiscover = TvDiscoverAdapter()
        adapterTrending = TrendingAdapter()
        adapterDiscover.notifyDataSetChanged()
        binding.rvTvDiscover.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvTrendingTvShow.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvTrendingTvShow.adapter = adapterTrending
        binding.rvTvDiscover.adapter = adapterDiscover
        onItemClick()
        getDataTrending()
        getData()
    }

    //onitemclick
    private fun onItemClick() {
        adapterDiscover.setOnItemClickCallback(object : TvDiscoverAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TvResultsItem) {
                val intentDetailActivity = Intent(activity, DetailTvActivity::class.java)
                intentDetailActivity.putExtra(DetailTvActivity.EXTRA_FILM, data)
                startActivity(intentDetailActivity)
            }

        })
        adapterTrending.setOnItemCLickCallback(object : TrendingAdapter.OnItemClickCallback {
            override fun onItemClick(data: Movie) {
                val intentDetailActivity = Intent(activity, DetailTvActivity::class.java)
                intentDetailActivity.putExtra(DetailTvActivity.EXTRA_FILM, data)
                startActivity(intentDetailActivity)
            }

        })
    }

    //getData
    private fun getDataTrending() {
        IdlingResources.increment()
        tvTrendingViewModel.setData()
        tvTrendingViewModel.getData().observe(viewLifecycleOwner, { TrendingList ->
            if (TrendingList !== null) {
                adapterTrending.setData(TrendingList)
            }
        })
        IdlingResources.decrement()
    }

    private fun getData(){
        retrofitViewModel.listTvResult.observe(viewLifecycleOwner, { DiscoverList ->
            if (DiscoverList !== null){
                val dataArray = DiscoverList as ArrayList<TvResultsItem>
                adapterDiscover.setData(DiscoverList)
            }
        })
    }
//    private fun getData() {
//        IdlingResources.increment()
//        tvDiscoverViewModel.setData()
//        tvDiscoverViewModel.getData().observe(viewLifecycleOwner, { MovieList ->
//            if (MovieList !== null) {
//                adapterDiscover.setData(MovieList)
//            }
//        })
//        IdlingResources.decrement()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}