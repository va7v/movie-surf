package com.example.tmdbapp.ui.new20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.buttomnavigation.databinding.FragmentNewBinding
import com.example.tmdbapp.utils.isNetworkAvailable

class NewMovieFragment : Fragment() {
    private var _binding: FragmentNewBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val randomViewModel by viewModels<NewMovieModel>()
    private val itemAdapter = NewMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView

        recyclerView.adapter = itemAdapter
        // app:layoutManager in layout vs recyclerView.layoutManager = LinearLayoutManager(this)

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

