package com.example.tmdbapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailsModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is movie details Fragment"
    }
    val text: LiveData<String> = _text
}