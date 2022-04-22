package com.example.tmdbapp.ui.random

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.buttomnavigation.R
import com.example.buttomnavigation.databinding.LayoutItemBinding
import com.example.tmdbapp.data.model.Movie
import com.squareup.picasso.Picasso

class RandomMoviesAdapter : RecyclerView.Adapter<RandomMoviesAdapter.ViewHolder>() {

    private val items = mutableListOf<Movie>()

    inner class ViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder(
            LayoutItemBinding.inflate(
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
            .into(holder.binding.imageView)
        holder.binding.titleTextView.text = items[position].title
        holder.binding.dateTextView.text = "Рейтинг: " + (items[position].vote_average).toString()

        val bundle = bundleOf("movie_id" to items[position].id,
        "title" to items[position].title, "rate" to items[position].vote_average.toString(),
        "poster_path" to items[position].poster_path, "date" to items[position].release_date,
        "overview" to items[position].overview)

        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_navigation_random_to_navigation_details, bundle)
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