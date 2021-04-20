package com.example.jetpack_submission1.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.databinding.ItemMovieBinding
import com.example.jetpack_submission1.model.MovieList

class MovieListViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(element: MovieList){
        binding.tvTitle.text = element.title
        binding.ratingMovie.rating = ((element.rating/2).toFloat())

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500"+element.poster)
            .into(binding.imgMovie)
    }
}