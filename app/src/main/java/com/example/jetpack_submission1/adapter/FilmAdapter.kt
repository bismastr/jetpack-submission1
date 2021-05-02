package com.example.jetpack_submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_submission1.adapter.viewholder.FilmViewHolder
import com.example.jetpack_submission1.databinding.ItemMovieBinding
import com.example.jetpack_submission1.model.MovieResultsItem
import com.example.jetpack_submission1.utils.IdlingResources

class FilmAdapter : RecyclerView.Adapter<FilmViewHolder>() {
    private var onItemCLickCallback: OnItemClickCallback? = null
    private val dataList = ArrayList<MovieResultsItem>()

    fun setData(movie: ArrayList<MovieResultsItem>) {
        dataList.clear()
        dataList.addAll(movie)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCLickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        IdlingResources.increment()
        holder.bindRetroFit(dataList[position])
        IdlingResources.decrement()
        holder.itemView.setOnClickListener {
            onItemCLickCallback?.onItemClicked(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieResultsItem)
    }
}