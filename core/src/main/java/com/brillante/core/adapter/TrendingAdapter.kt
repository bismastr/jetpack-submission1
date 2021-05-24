package com.brillante.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brillante.core.databinding.ItemTrendingBinding
import com.brillante.core.domain.model.MovieDiscover

class TrendingAdapter : RecyclerView.Adapter<com.brillante.core.adapter.viewholder.TrendingViewHolder>() {
    private var onItemClickCallback: com.brillante.core.adapter.TrendingAdapter.OnItemClickCallback? = null
    private val dataList = ArrayList<MovieDiscover>()

    fun setData(movie: ArrayList<MovieDiscover>) {
        dataList.clear()
        dataList.addAll(movie)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onItemClickCallback: com.brillante.core.adapter.TrendingAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.brillante.core.adapter.viewholder.TrendingViewHolder {
        return com.brillante.core.adapter.viewholder.TrendingViewHolder(
            ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: com.brillante.core.adapter.viewholder.TrendingViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClick(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface OnItemClickCallback {
        fun onItemClick(data: MovieDiscover)
    }
}