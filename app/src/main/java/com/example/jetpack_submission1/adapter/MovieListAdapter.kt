package com.example.jetpack_submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_submission1.adapter.viewholder.MovieListViewHolder
import com.example.jetpack_submission1.databinding.ItemMovieBinding
import com.example.jetpack_submission1.model.MovieList

class MovieListAdapter: RecyclerView.Adapter<MovieListViewHolder>() {
    private val dataList = ArrayList<MovieList>()

    fun setData(movie: ArrayList<MovieList>){
        dataList.clear()
        dataList.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}