package com.example.tmdbapp.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buttomnavigation.databinding.FragmentTopBinding
import com.example.tmdbapp.utils.isNetworkAvailable

class TopMoviesFragment : Fragment() {
    private var _binding: FragmentTopBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val topViewModel by viewModels<TopMoviesModel>()
    private val itemAdapter = TopMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.adapter = itemAdapter
        // in layout instead of recyclerView.layoutManager = LinearLayoutManager(this)

        if (isNetworkAvailable(requireContext())) {
            topViewModel.items.observe(viewLifecycleOwner, Observer {
                itemAdapter.addItems(it)
            })
            topViewModel.status.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it , Toast.LENGTH_LONG).show()
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
