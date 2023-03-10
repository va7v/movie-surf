package com.example.tmdbapp.ui.new20

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.buttomnavigation.R
import com.example.buttomnavigation.databinding.ItemNewMovieBinding
import com.example.tmdbapp.data.network.Movie
import com.squareup.picasso.Picasso

class NewMoviesAdapter : RecyclerView.Adapter<NewMoviesAdapter.ViewHolder>() {

    private val items = mutableListOf<Movie>()

    inner class ViewHolder(val binding: ItemNewMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(
            ItemNewMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount() : Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(BASE_IMAGE_URL + items[position].poster_path)
            .placeholder(R.drawable.ic_baseline_movies_120)
            .error(R.drawable.ic_baseline_movies_120)
            .into(holder.binding.imageView)
        holder.binding.titleTextView.text = items[position].title

        holder.binding.dateTextView.text = "(${(items[position].release_date)})"+
                "\n\nРейтинг:\n" + (items[position].vote_average)

        val bundle = bundleOf(
            "movie_id" to items[position].id,
            "title" to items[position].title,
            "rate" to items[position].vote_average.toString(),
            "poster_path" to items[position].poster_path,
            "date" to items[position].release_date,
            "overview" to items[position].overview)

        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_navigation_new_to_navigation_details, bundle)
        )
    }

    fun addItems(newItems: List<Movie>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }
}