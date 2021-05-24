package com.example.jetpack_submission1.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpack_submission1.adapter.TrendingAdapter
import com.example.jetpack_submission1.adapter.TvDiscoverAdapter
import com.example.jetpack_submission1.data.Resource
import com.example.jetpack_submission1.databinding.FragmentTvshowBinding
import com.example.jetpack_submission1.domain.model.MovieDiscover
import com.example.jetpack_submission1.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding as FragmentTvshowBinding


    //viewModel
    private val tvViewModel: TvViewModel by viewModel()

    //adapter
    private lateinit var adapterDiscover: TvDiscoverAdapter
    private lateinit var adapterTrending: TrendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
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
            override fun onItemClicked(data: MovieDiscover) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 1)
                startActivity(intentDetailActivity)
            }

        })
        adapterTrending.setOnItemCLickCallback(object : TrendingAdapter.OnItemClickCallback {
            override fun onItemClick(data: MovieDiscover) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 1)
                startActivity(intentDetailActivity)
            }
        })
    }

    //getData
    private fun getData() {

        tvViewModel.tvDiscover.observe(viewLifecycleOwner, { TvList ->
            if (TvList !== null) {
                when (TvList) {
                    is Resource.Loading -> showDiscoverLoading(true)
                    is Resource.Success -> {
                        showDiscoverLoading(false)
                        val tvArrayList = TvList.data as ArrayList<MovieDiscover>
                        adapterDiscover.setData(tvArrayList)
                    }
                    is Resource.Error -> Log.d("TAG", "Get data TvDiscover error")
                }
            }
        })

    }

    private fun getDataTrending() {
        tvViewModel.tvTrending.observe(viewLifecycleOwner, { TvTrending ->
            if (TvTrending != null) {
                when (TvTrending) {
                    is Resource.Loading -> showTrendingLoading(true)
                    is Resource.Success -> {
                        showTrendingLoading(false)
                        val tvArrayList = TvTrending.data as ArrayList<MovieDiscover>
                        adapterTrending.setData(tvArrayList)
                    }
                    is Resource.Error -> Log.d("TAG", "Get data TvTrending error")
                }
            }
        })
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