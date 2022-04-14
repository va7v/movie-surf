package com.example.tmdbapp.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buttomnavigation.databinding.FragmentRandomBinding
import com.example.tmdbapp.ui.MoviesAdapter

class RandomMovieFragment : Fragment() {
    private var _binding: FragmentRandomBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val randomViewModel by viewModels<RandomMovieModel>()
    private val itemAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.adapter = itemAdapter
        // @ xml vs recyclerView.layoutManager = LinearLayoutManager(this)

        randomViewModel.items.observe(this, Observer {
            itemAdapter.addItems(it)
        })
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

