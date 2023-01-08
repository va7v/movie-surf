package com.example.tmdbapp.ui.new20

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.network.Movie
import com.example.tmdbapp.repositories.TmdbApiImpl
import kotlinx.coroutines.launch

class NewMovieModel : ViewModel() {
    private val _items = MutableLiveData<List<Movie>>()
    val items: LiveData<List<Movie>> get() = _items
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                _items.value = TmdbApiImpl.loadMovies()
            } catch (e: Exception) {
                _status.value = "Ошибка сетевого запроса: ${e.message}"
            }
        }
    }

}
