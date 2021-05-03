package com.example.jetpack_submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_submission1.adapter.viewholder.TrendingViewHolder
import com.example.jetpack_submission1.data.local.entity.MovieDiscoverEntity
import com.example.jetpack_submission1.databinding.ItemTrendingBinding

class TrendingAdapter : RecyclerView.Adapter<TrendingViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val dataList = ArrayList<MovieDiscoverEntity>()

    fun setData(movie: ArrayList<MovieDiscoverEntity>) {
        dataList.clear()
        dataList.addAll(movie)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClick(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface OnItemClickCallback {
        fun onItemClick(data: MovieDiscoverEntity)
    }
}