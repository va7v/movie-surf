package com.example.tmdbapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buttomnavigation.databinding.LayoutItemBinding
import com.example.tmdbapp.remoteSource.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val items = mutableListOf<Movie>() //moviesList

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
        holder.binding.titleTextView.text = items[position].title + "  (" +
                items[position].vote_average.toString() + ")"
        holder.binding.dateTextView.text = "(с) " + items[position].data
    }
    fun addItems(newItems: List<Movie>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}