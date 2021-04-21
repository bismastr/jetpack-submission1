package com.example.jetpack_submission1.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.databinding.ItemMovieBinding
import com.example.jetpack_submission1.model.Movie

class FilmViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(element: Movie){
        binding.tvTitle.text = element.title
        binding.ratingMovie.rating = ((element.rating/2).toFloat())
        binding.tvRating.text = element.rating.toString()

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500"+element.poster)
            .into(binding.imgMovie)
    }
}