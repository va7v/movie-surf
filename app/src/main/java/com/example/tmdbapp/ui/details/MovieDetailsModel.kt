package com.example.tmdbapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.model.Actor
import com.example.tmdbapp.repositories.API_KEY
import com.example.tmdbapp.repositories.TmdbApiImpl
import kotlinx.coroutines.launch

class MovieDetailsModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private val _items = MutableLiveData<List<Actor>>()
    val items: LiveData<List<Actor>> get() = _items

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getMovieGentre(m_id: String?) {
        viewModelScope.launch {
            try {
                _text.value = TmdbApiImpl.getDetails(m_id, API_KEY).get(0).name
            } catch (e: Exception) {
                _status.value = "Ошибка сетевого запроса: ${e.message}"
            }
        }
    }

    fun getMovieActors(m_id: String?) {
        viewModelScope.launch {
            try {
                _items.value = TmdbApiImpl.getActors(m_id, API_KEY)
            } catch (e: Exception) {
                _status.value = "Ошибка сетевого запроса: ${e.message}"
            }
        }
    }
}

