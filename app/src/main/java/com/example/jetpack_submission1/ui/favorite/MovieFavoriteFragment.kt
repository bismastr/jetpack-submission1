package com.example.jetpack_submission1.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.databinding.FragmentMovieFavoriteBinding
import com.example.jetpack_submission1.ui.detail.DetailActivity
import com.example.jetpack_submission1.utils.IdlingResources
import com.example.jetpack_submission1.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment() {
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding as FragmentMovieFavoriteBinding

    private lateinit var adapter: FavoritePagedListAdapter

    private lateinit var favoriteViewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
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

    private fun onItemClick(){
        adapter.setOnItemCLickCallback(object: FavoritePagedListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: FavoriteEntity) {
                val intentDetailActivity = Intent(activity, DetailActivity::class.java)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FILM, data)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FROM, 0)
                intentDetailActivity.putExtra(DetailActivity.EXTRA_FAVO, true)
                startActivity(intentDetailActivity)
            }

        })
    }

    private fun getData(){
        IdlingResources.increment()
        favoriteViewModel.getAllMovie(0).observe(viewLifecycleOwner, {MovieList ->
            if (MovieList !== null){
                adapter.setData(MovieList)
            }
        })
        IdlingResources.decrement()
    }

    private fun setupRecyclerView() {
        adapter = FavoritePagedListAdapter()
        binding.rvMovieFavorite.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvMovieFavorite.adapter = adapter
        getData()
    }


}