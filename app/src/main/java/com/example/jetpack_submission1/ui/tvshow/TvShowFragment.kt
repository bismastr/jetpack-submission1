package com.example.jetpack_submission1.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpack_submission1.adapter.TrendingAdapter
import com.example.jetpack_submission1.adapter.TvDiscoverAdapter
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.databinding.FragmentTvshowBinding
import com.example.jetpack_submission1.ui.detail.DetailTvActivity
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!


    //viewModel
    private lateinit var tvViewModel: TvViewModel
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
        //New ViewModel
        val factory = ViewModelFactory.getInstance(requireActivity())
        tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

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
            override fun onItemClicked(data: MovieDiscoverEntity) {
                val intentDetailActivity = Intent(activity, DetailTvActivity::class.java)
                intentDetailActivity.putExtra(DetailTvActivity.EXTRA_FILM, data)
                startActivity(intentDetailActivity)
            }

        })
        adapterTrending.setOnItemCLickCallback(object : TrendingAdapter.OnItemClickCallback {
            override fun onItemClick(data: MovieDiscoverEntity) {
                val intentDetailActivity = Intent(activity, DetailTvActivity::class.java)
                intentDetailActivity.putExtra(DetailTvActivity.EXTRA_FILM, data)
                startActivity(intentDetailActivity)
            }

        })
    }

    //getData
    private fun getData(){
        IdlingResources.increment()
        showDiscoverLoading(true)
        tvViewModel.getTvDiscover().observe(viewLifecycleOwner, {TvList ->
            if (TvList !== null){
                val tvArray = TvList as ArrayList<MovieDiscoverEntity>
                showDiscoverLoading(false)
                adapterDiscover.setData(tvArray)
            }
        })
        IdlingResources.decrement()
    }

    private fun getDataTrending(){
        IdlingResources.increment()
        showTrendingLoading(true)
        tvViewModel.getTvTrending().observe(viewLifecycleOwner, {TrendingList ->
            if (TrendingList !== null){
                val trendingArray = TrendingList as ArrayList<MovieDiscoverEntity>
                showTrendingLoading(false)
                adapterTrending.setData(trendingArray)
            }
        })
        IdlingResources.decrement()
    }

    private fun showDiscoverLoading(state: Boolean) {
        if (state) {
            binding.shimmerDiscoverTV.startShimmer()
            binding.shimmerDiscoverTV.visibility = View.VISIBLE
            binding.rvTvDiscover.visibility = View.GONE

        } else {
            binding.shimmerDiscoverTV.stopShimmer()
            binding.shimmerDiscoverTV.visibility = View.GONE
            binding.rvTvDiscover.visibility = View.VISIBLE
        }
    }

    private fun showTrendingLoading(state: Boolean) {
        if (state) {
            binding.shimmerTrendingTv.startShimmer()
            binding.shimmerTrendingTv.visibility = View.VISIBLE
            binding.rvTrendingTvShow.visibility = View.GONE


        } else {
            binding.shimmerTrendingTv.stopShimmer()
            binding.shimmerTrendingTv.visibility = View.GONE
            binding.rvTrendingTvShow.visibility = View.VISIBLE

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}