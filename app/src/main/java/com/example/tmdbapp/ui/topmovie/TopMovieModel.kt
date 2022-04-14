package com.example.tmdbapp.ui.topmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.repositories.TmdbApiImpl
import kotlinx.coroutines.launch

class TopMovieModel : ViewModel() {
    private val _items = MutableLiveData<List<Movie>>()
    val items: LiveData<List<Movie>> get() = _items

    init {
        viewModelScope.launch {
            _items.value = TmdbApiImpl.loadTopMovies() //
        }
    }

}


