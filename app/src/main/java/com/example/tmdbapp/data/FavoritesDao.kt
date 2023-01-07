package com.example.tmdbapp.data
/*
import android.content.SharedPreferences
import android.widget.TableRow
import com.example.tmdbapp.data.network.Movie
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface FavoritesDao {
    /**
     * add [movie] to favorites
     */
    fun add(movie: Movie)
    fun delete(movie: Movie)
    /**
     * @return list of favorite films
     * can be empty
     */
    fun getAll(): List<Movie>
    fun isInFavorites(movie: Movie): Boolean
}

class FavoritesDaoImpl(
    private val sharedPreferences: SharedPreferences
): FavoritesDao {
    private var movies: List<Movie>
        get() = sharedPreferences.getString(FAVORITES_DAO_KEY, null)?.let {
            try {
                Json.decodeFromString<List<com.example.tmdbapp.data.Movie>>(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()
        set(value) {
            sharedPreferences.edit().putString(FAVORITES_DAO_KEY, Json.encodeToString(value))
                .apply()
        }
    override fun add(movie: Movie) { // sharedPreferences.edit().putString()
        movies = movies + movie
    }

    override fun delete(movie: Movie) {
        movies = movies.filter { it != movie }
    }

    override fun getAll(): List<Movie> = movies

    override fun isInFavorites(movie: Movie): Boolean = movies.contains(movie)

    companion object {
        private const val FAVORITES_DAO_KEY = "FAVORITES_DAO_KEY"
    }
}

 */