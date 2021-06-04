package com.example.jetpack_submission1.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.brillante.core.databinding.ItemMovieBinding
import com.brillante.core.domain.model.MovieDiscover
import com.bumptech.glide.Glide


class FilmViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(element: MovieDiscover) {
        binding.tvTitle.text = element.title
        binding.ratingMovie.rating = ((element.rating / 2).toFloat())
        binding.tvRating.text = element.rating.toString()

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500" + element.poster)
            .into(binding.imgMovie)
    }
}