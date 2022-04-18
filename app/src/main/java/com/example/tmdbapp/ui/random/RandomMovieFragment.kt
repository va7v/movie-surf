package com.example.tmdbapp.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buttomnavigation.databinding.FragmentRandomBinding
import com.example.tmdbapp.utils.isNetworkAvailable

class RandomMovieFragment : Fragment() {
    private var _binding: FragmentRandomBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val randomViewModel by viewModels<RandomMovieModel>()
    private val itemAdapter = RandomMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.adapter = itemAdapter
        // app:layoutManager in layout instead of recyclerView.layoutManager = LinearLayoutManager(this)

        if (isNetworkAvailable(requireContext())) {
            randomViewModel.items.observe(viewLifecycleOwner, Observer {
                itemAdapter.addItems(it)
            })

            randomViewModel.status.observe(viewLifecycleOwner, Observer {
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

