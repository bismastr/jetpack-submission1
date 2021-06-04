package com.brillante.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.brillante.core.domain.model.MovieDiscover
import com.brillante.favorite.databinding.FragmentMovieFavoriteBinding
import com.example.jetpack_submission1.adapter.FilmAdapter
import com.example.jetpack_submission1.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavoriteFragment : Fragment() {
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding as FragmentMovieFavoriteBinding

    private lateinit var adapter: FilmAdapter

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        onItemClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick() {
        adapter.setOnItemCLickCallback(object : FilmAdapter.OnItemClickCallback {

            override fun onItemClicked(data: MovieDiscover) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 0)
                startActivity(intentDetailActivity)
            }

        })
    }

    private fun getData() {
        favoriteViewModel.getMovieFavorite().observe(viewLifecycleOwner, { MovieList ->
            if (MovieList !== null) {
                MovieList as ArrayList
                adapter.setData(MovieList)
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = FilmAdapter()
        binding.rvMovieFavorite.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvMovieFavorite.adapter = adapter
        getData()
    }


}