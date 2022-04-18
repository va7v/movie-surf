package com.example.tmdbapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.repositories.API_KEY
import com.example.tmdbapp.repositories.TmdbApiImpl
import kotlinx.coroutines.launch

class MovieDetailsModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getMovie(m_id: String?) {
        viewModelScope.launch {
            try {
                _text.value = TmdbApiImpl.getDetails(m_id, API_KEY).get(0).name
            } catch (e: Exception) {
                _text.value = "Ошибка сетевого запроса: ${e.message}"
            }
        }
    }

}

