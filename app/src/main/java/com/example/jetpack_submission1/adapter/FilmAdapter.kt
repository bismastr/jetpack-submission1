package com.example.jetpack_submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_submission1.adapter.viewholder.FilmViewHolder
import com.example.jetpack_submission1.databinding.ItemMovieBinding
import com.example.jetpack_submission1.model.Movie

class FilmAdapter: RecyclerView.Adapter<FilmViewHolder>() {

    private val dataList = ArrayList<Movie>()

    fun setData(movie: ArrayList<Movie>){
        dataList.clear()
        dataList.addAll(movie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}