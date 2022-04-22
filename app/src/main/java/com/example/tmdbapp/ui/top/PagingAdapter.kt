package com.example.tmdbapp.ui.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.buttomnavigation.databinding.ItemUserBinding
import com.example.tmdbapp.data.model.Movie

class PagingAdapter: PagingDataAdapter<Movie, PagingAdapter.Holder>(UsersDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = getItem(position) ?: return
        with (holder.binding) {
            userNameTextView.text = movie.title
            userCompanyTextView.text = movie.release_date
            //loadUserPhoto(photoImageView, user.imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

/*    private fun loadUserPhoto(imageView: ImageView, url: String) {
        val context = imageView.context
        if (url.isNotBlank()) {

                .placeholder(R.drawable.ic_user_avatar)
                .error(R.drawable.ic_user_avatar)
                .into(imageView)
        } else {

                .into(imageView)
        }
    }*/

    class Holder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)

}

// ---

class UsersDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}