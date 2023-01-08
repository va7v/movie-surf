package com.example.tmdbapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.buttomnavigation.databinding.FragmentDetailsTvshowBinding
import com.squareup.picasso.Picasso

class TvShowDetailsFragment(): Fragment() {

    private var _binding: FragmentDetailsTvshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsTvshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imageView: ImageView = binding.imageView
        Picasso.get().load(BASE_IMAGE_URL +
                arguments?.getString("poster_path")).into(imageView)
        val textViewDetails: TextView = binding.textDetails1
        val textViewDetails2: TextView = binding.textDetails2

            val MovieDate = arguments?.getString("date")
            val MovieOverview = arguments?.getString("overview")
            val MovieRate = arguments?.getString("rate")
        textViewDetails.text =
            "${arguments?.getString("title")}\n\nДата первого эфира:\n$MovieDate\n\nРейтинг: $MovieRate"
            textViewDetails2.text = "Сюжет:  " + MovieOverview

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