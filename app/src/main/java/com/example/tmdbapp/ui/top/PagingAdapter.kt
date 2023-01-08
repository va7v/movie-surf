package com.example.tmdbapp.ui.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.buttomnavigation.R
import com.example.buttomnavigation.databinding.ItemTopMovieBinding
import com.example.tmdbapp.data.network.Movie
import com.example.tmdbapp.ui.new20.NewMoviesAdapter
import com.squareup.picasso.Picasso

class PagingAdapter: PagingDataAdapter<Movie, PagingAdapter.Holder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = getItem(position) ?: return
        with (holder.binding) {
            titleTextView.text = movie.title
            if (!movie.release_date.isNullOrEmpty()) {
                dateTextView.text = "("+movie.release_date.substring(0, 4) + ")\n\nРейтинг:\n" +
                        movie.vote_average.toString()
            } else dateTextView.text = "\n\nРейтинг:\n" + movie.vote_average.toString()
            Picasso.get().load(NewMoviesAdapter.BASE_IMAGE_URL + movie.poster_path)
                .placeholder(R.drawable.ic_baseline_movies_120)
                .error(R.drawable.ic_baseline_movies_120)
                .into(holder.binding.imageView)

        }
        val bundle = bundleOf(
            "movie_id" to movie.id,
            "title" to movie.title,
            "rate" to movie.vote_average.toString(),
            "poster_path" to movie.poster_path,
            "date" to movie.release_date,
            "overview" to movie.overview)

        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_navigation_top_to_navigation_details, bundle)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTopMovieBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    class Holder(
        val binding: ItemTopMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

}


class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}