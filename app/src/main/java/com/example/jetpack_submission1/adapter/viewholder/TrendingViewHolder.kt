package com.example.jetpack_submission1.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.brillante.core.databinding.ItemTrendingBinding
import com.brillante.core.domain.model.MovieDiscover
import com.bumptech.glide.Glide


class TrendingViewHolder(private val binding: ItemTrendingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(element: MovieDiscover) {
        binding.tvTitleTrending.text = element.title
        binding.tvRatingTrending.text = element.rating.toString()

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w500" + element.poster)
            .into(binding.imgTrending)
    }
}