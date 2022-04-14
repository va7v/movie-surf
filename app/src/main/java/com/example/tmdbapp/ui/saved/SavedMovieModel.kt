package com.example.tmdbapp.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedMovieModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is saved movies Fragment"
    }
    val text: LiveData<String> = _text
}