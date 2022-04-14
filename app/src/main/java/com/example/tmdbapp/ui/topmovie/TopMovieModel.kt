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

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getTopMovies()
    }

    private fun getTopMovies() {
        viewModelScope.launch {
            try {
                _items.value = TmdbApiImpl.loadTopMovies()
             } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}


