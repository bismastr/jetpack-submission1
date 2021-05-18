package com.example.jetpack_submission1.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack_submission1.data.local.entity.FavoriteEntity
import com.example.jetpack_submission1.databinding.ItemMovieBinding

class FavoritePagedListAdapter :
    PagedListAdapter<FavoriteEntity, FavoritePagedListAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    private val favoList = ArrayList<FavoriteEntity>()
    private var onItemCLickCallback: OnItemClickCallback? = null

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteEntity> =
            object : DiffUtil.ItemCallback<FavoriteEntity>() {
                override fun areItemsTheSame(
                    oldFav: FavoriteEntity,
                    newFav: FavoriteEntity
                ): Boolean {
                    return oldFav.id == newFav.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldFav: FavoriteEntity,
                    newFav: FavoriteEntity
                ): Boolean {
                    return oldFav == newFav
                }
            }
    }

    fun setData(favoData: List<FavoriteEntity>) {
        favoList.clear()
        favoList.addAll(favoData)
        notifyDataSetChanged()

    }

    fun setOnItemCLickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCLickCallback = onItemClickCallback
    }

    class FavoriteViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: FavoriteEntity) {
            binding.tvTitle.text = element.title
            binding.ratingMovie.rating = ((element.rating / 2).toFloat())
            binding.tvRating.text = element.rating.toString()

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + element.poster)
                .into(binding.imgMovie)
        }

    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoList[position])
        holder.itemView.setOnClickListener{
            onItemCLickCallback?.onItemClicked(favoList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FavoriteEntity)
    }

    override fun getItemCount(): Int {
        return favoList.size
    }


}