package com.example.tmdbapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.buttomnavigation.R
import com.example.buttomnavigation.databinding.FragmentSearchBinding

class SearchMovieFragment : Fragment() {

    private lateinit var searchMovieModel: SearchMovieModel
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchMovieModel =
            ViewModelProvider(this).get(SearchMovieModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        searchMovieModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        val searchText: EditText = binding.editTextSearch


        val buttonSearch: Button = binding.button

        buttonSearch.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_navigation_search_to_navigation_result
            //    , bundleOf("query" to searchText.id)
            )
        )
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}