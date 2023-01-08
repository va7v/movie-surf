package com.example.tmdbapp.ui.top

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.tmdbapp.data.network.Movie
import com.example.tmdbapp.repositories.TmdbApiImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@FlowPreview
class TopMoviesModel : ViewModel() {
//    private val _items = MutableLiveData<List<Movie>>()
//    val items: LiveData<List<Movie>> get() = _items

//    private val _status = MutableLiveData<String>()
//    val status: LiveData<String> = _status

    val moviesFlow: Flow<PagingData<Movie>>

    init {
 //       getTopMovies()
        moviesFlow = TmdbApiImpl.getPagedMovies()
            //.cachedIn(viewModelScope)
    }
/*

    private fun getTopMovies() {
        viewModelScope.launch {
            try {
                _items.value = TmdbApiImpl.loadTopMovies()
             } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
*/

}


