package com.example.moviedb.ui.screen.popularmovie

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.ui.base.BaseRecyclerAdapter

class PopularMovieAdapter(
    val itemClickListener: ((Movie, Int) -> Unit)? = null
) : BaseRecyclerAdapter<Movie, ItemMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        Log.e("areItemsTheSame","${oldItem.id} ${newItem.id}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        Log.e("areContentsTheSame","${oldItem.id} ${newItem.id}")
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie
    }

    override fun bindFirstTime(binding: ItemMovieBinding) {

    }

    override fun bindView(binding: ItemMovieBinding, item: Movie, position: Int) {
        super.bindView(binding, item, position)
        binding.apply {
            root.setOnClickListener {
                item.apply {
                    itemClickListener?.invoke(this, position)
                }
            }
        }
    }
}