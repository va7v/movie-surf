package com.example.tmdbapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMovieModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search movies Fragment"
    }
    val text: LiveData<String> = _text
}