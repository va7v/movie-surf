package com.example.tmdbapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.buttomnavigation.R
import com.example.buttomnavigation.databinding.FragmentDetailsBinding
import com.example.buttomnavigation.databinding.ItemActorBinding
import com.squareup.picasso.Picasso

class MovieDetailsFragment(): Fragment() {

    private lateinit var movieDetailsModel: MovieDetailsModel
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailsModel = ViewModelProvider(this).get(MovieDetailsModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imageView: ImageView = binding.imageView
        Picasso.get().load(BASE_IMAGE_URL +
                arguments?.getString("poster_path")).into(imageView)
        val textViewDetails: TextView = binding.textDetails1
        val textViewDetails2: TextView = binding.textDetails2
        movieDetailsModel.getMovieGentre(arguments?.getString("movie_id"))
        movieDetailsModel.text.observe(getViewLifecycleOwner(), Observer {
            var gentre = it
            val MovieDate = arguments?.getString("date")
            val MovieOverview = arguments?.getString("overview")
            val MovieRate = arguments?.getString("rate")
            textViewDetails.text = arguments?.getString("title") +
                    "\n\nДата выхода: " + MovieDate +
                    "\nЖанр: " + gentre +
                    "\nРейтинг: " + MovieRate
            textViewDetails2.text = "Сюжет:  " + MovieOverview
        })
        movieDetailsModel.getMovieActors(arguments?.getString("movie_id"))
        movieDetailsModel.items.observe(getViewLifecycleOwner(), Observer {
            /*
            val size = it.size - 1
            var str = "В ролях:\n${it.get(0).name}"
            for (n in 1 .. size) { str = str + ", " + it.get(n).name}
            textViewActor.text = str
            */
            val linLayout = binding.linLayout
            for (i in 0 until it.size) {
                val itemBinding = ItemActorBinding.inflate(LayoutInflater.from(requireContext()))
                val tvName = itemBinding.tvName
                tvName.text = it[i].name + ":"
                val character = itemBinding.character
                character.text = it[i].character
                val imageViewCast = itemBinding.imageViewCast
                Picasso.get().load(BASE_IMAGE_URL + it[i].profile_path)
                    .placeholder(R.drawable.placeholder_150)
                    .error(R.drawable.placeholder_150)
                    .into(imageViewCast)
                linLayout.addView(itemBinding.root)
            }
        })

        movieDetailsModel.status.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w200"
    }
}