package com.example.tmdbapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.repositories.TmdbApiImpl
import kotlinx.coroutines.launch

class MovieDetailsModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "This is movie details Fragment"
        viewModelScope.launch {
            value = "Gentre is:  " + TmdbApiImpl.getDetails().get(0).name
        }
    }
    val text: LiveData<String> = _text

}

