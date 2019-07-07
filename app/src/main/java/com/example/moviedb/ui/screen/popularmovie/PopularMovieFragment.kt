package com.example.moviedb.ui.screen.popularmovie

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.example.moviedb.ui.base.BaseLoadMoreRefreshFragment
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMovieFragment :
    BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, PopularMovieViewModel, Movie>() {

    /*companion object {
        const val TAG = "PopularMovieFragment"
        const val TYPE = "TYPE"

        fun newInstance(type: Int) = PopularMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE, type)
            }
        }
    }*/

    override val viewModel: PopularMovieViewModel by viewModel()

    private val args: PopularMovieFragmentArgs by navArgs()

    lateinit var adapter: PopularMovieAdapter
    lateinit var adapter2: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*viewModel.apply {
            mode.value = args.type?.toInt()
        }*/

        adapter = PopularMovieAdapter(
            itemClickListener = { movie, position ->
                //                toMovieDetail(it)
                val list = viewModel.listItem.value
                val oldMovie = list?.get(position)
                val newMovie = list?.get(position)?.copy()
                newMovie?.title = "123"
                if (newMovie != null) {
                    list[position] = newMovie
                    viewModel.listItem.value = list
                }
//                adapter.notifyItemChanged(position)
            }
        )

        adapter2 = MovieAdapter(
            listITem = viewModel.listItem.value ?: arrayListOf<Movie>(),
            itemClickListener = { movie, position ->

            }
        )

        container.setBackgroundColor(Color.BLACK)
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = this@PopularMovieFragment.adapter
        }

        viewModel.apply {
            listItem.observe(viewLifecycleOwner, Observer {
                Log.e("observe", it.hashCode().toString())
                Log.e("observe0", it[0].hashCode().toString())
                adapter.submitList(it)

                adapter2.listITem = it
                adapter2.notifyDataSetChanged()
            })
            firstLoad()
        }
    }

    private fun toMovieDetail(movie: Movie) {
        findNavController().navigate(
            PopularMovieFragmentDirections.toMovieDetail(movie)
        )
    }
}