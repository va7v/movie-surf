package com.example.tmdbapp.ui.topmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buttomnavigation.databinding.FragmentTopBinding
import com.example.tmdbapp.ui.MoviesAdapter

class TopFragment : Fragment() {
    private var _binding: FragmentTopBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val topViewModel by viewModels<TopMovieModel>()
    private val itemAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.adapter = itemAdapter
        // @ xml vs recyclerView.layoutManager = LinearLayoutManager(this)

        topViewModel.items.observe(this, Observer {
            itemAdapter.addItems(it)
        })
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


/*

class TopFragment : Fragment() {

    private lateinit var topMovieModel: TopMovieModel
    private var _binding: FragmentTopBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topMovieModel = ViewModelProvider(this).get(TopMovieModel::class.java)

        _binding = FragmentTopBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTopMovie
        topMovieModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}*/
