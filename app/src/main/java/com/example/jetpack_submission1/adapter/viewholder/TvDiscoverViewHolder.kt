package com.example.jetpack_submission1.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.databinding.ItemMovieBinding
import com.example.jetpack_submission1.model.TvResultsItem

class TvDiscoverViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(element: TvResultsItem) {
        binding.tvTitle.text = element.originalName
        binding.ratingMovie.rating = ((element.voteAverage / 2).toFloat())
        binding.tvRating.text = element.voteAverage.toString()

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500" + element.posterPath)
            .into(binding.imgMovie)
    }
}