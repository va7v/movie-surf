package com.example.tmdbapp.ui.details

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.buttomnavigation.databinding.FragmentDetailsBinding
import com.example.tmdbapp.ui.random.RandomMoviesAdapter
import com.example.tmdbapp.utils.isNetworkAvailable
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
        val textView: TextView = binding.textNotifications
        val imageView: ImageView = binding.imageView
        Picasso.get().load(RandomMoviesAdapter.BASE_IMAGE_URL +
             arguments?.getString("poster_path")).into(imageView)
        if (isNetworkAvailable(requireContext())) {
            movieDetailsModel.getMovie(arguments?.getString("movie_id"))
            movieDetailsModel.text.observe(getViewLifecycleOwner(), Observer {
                textView.text = arguments?.getString("title")+"\n" +it+"\nРейтинг: "+
                        arguments?.getString("rate")
            })
            movieDetailsModel.status.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        } else
            Toast.makeText(requireContext(), "Network is off!", Toast.LENGTH_LONG).show()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}