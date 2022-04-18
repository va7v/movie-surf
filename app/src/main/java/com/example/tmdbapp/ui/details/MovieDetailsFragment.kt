package com.example.tmdbapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.buttomnavigation.databinding.FragmentSavedBinding

class MovieDetailsFragment(): Fragment() {

    private lateinit var movieDetailsModel: MovieDetailsModel
    private var _binding: FragmentSavedBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailsModel = ViewModelProvider(this).get(MovieDetailsModel::class.java)

        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textNotifications
        movieDetailsModel.getMovie(arguments?.getString("movie_id"))
        movieDetailsModel.text.observe(getViewLifecycleOwner(), Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}