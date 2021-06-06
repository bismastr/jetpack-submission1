package com.brillante.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brillante.core.domain.model.MovieDiscover
import com.brillante.favorite.databinding.FragmentTvFavoriteBinding
import com.example.jetpack_submission1.adapter.FilmAdapter
import com.example.jetpack_submission1.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class TvFavoriteFragment : Fragment() {
    private var _binding: FragmentTvFavoriteBinding? = null
    private val binding get() = _binding as FragmentTvFavoriteBinding

    private lateinit var adapter: FilmAdapter

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvFavoriteBinding.inflate(inflater, container, false)
//        val factory = ViewModelFactory.getInstance(requireActivity())
//        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        onItemClick()
    }

    private fun onItemClick() {
        adapter.setOnItemCLickCallback(object : FilmAdapter.OnItemClickCallback {

            override fun onItemClicked(data: MovieDiscover) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 1)
                startActivity(intentDetailActivity)
            }

        })
    }

    private fun getData() {

        favoriteViewModel.getTvFavorite().observe(viewLifecycleOwner, { TvList ->
            if (TvList !== null) {
                TvList as ArrayList
                adapter.setData(TvList)
            }

        })

    }

    private fun setupRecyclerView() {
        adapter = FilmAdapter()
        binding.rvTvFavorite.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvTvFavorite.adapter = adapter
        getData()
    }

    override fun onDestroyView() {
        binding.rvTvFavorite.adapter = null
        super.onDestroyView()
        _binding = null
    }


}