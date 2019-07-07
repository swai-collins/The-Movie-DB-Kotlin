package com.example.moviedb.ui.screen.popularmovie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.ItemMovieBinding
import com.example.moviedb.ui.base.BaseViewHolder

class MovieAdapter(
    var listITem: ArrayList<Movie> = ArrayList(),
    val itemClickListener: ((Movie, Int) -> Unit)? = null
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun getItemCount(): Int = listITem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        Log.e("NewAdapter",position.toString())
        val movie = listITem[position]
        holder.binding.item = movie
        holder.binding.root.setOnClickListener {
            itemClickListener?.invoke(movie, position)
        }
    }

    class MovieHolder(binding: ItemMovieBinding) : BaseViewHolder<ItemMovieBinding>(binding)
}
