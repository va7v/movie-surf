package com.example.tmdbapp.repositories

import com.example.tmdbapp.remoteSource.Movie
import com.example.tmdbapp.remoteSource.popular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TMDBApi {
    @GET("/3/movie/top_rated?") //top_rated vs popular
    suspend fun loadMoviesList(): popular
    @GET("/3/search/movie?&language=en-US&query=movie&page=1&include_adult=false&primary_release_year=2022")
    suspend fun loadSomeMovies(): popular
    //@GET("/3/movie/{id}")   getDetails(@Path("id") id : String, @Query("api_key") api_key: String)
}
object TMDBApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
    private val TMDBApiService = retrofit.create(TMDBApi::class.java)

    suspend fun loadMoviesList() : List<Movie> {
        return withContext(Dispatchers.IO) {
            TMDBApiService.loadMoviesList()
                .results
                .map { result ->
                    Movie(
                        result.id,
                        result.title, // original_title  in exotic langs & super long
                        result.release_date,
                        result.vote_average
                    )
                }
        }
    }
    suspend fun loadSomeMovies() : List<Movie> {
        return withContext(Dispatchers.IO) {
            TMDBApiService.loadSomeMovies()
                .results
                .map { result ->
                    Movie(
                        result.id,
                        result.title,
                        result.release_date,
                        result.vote_average
                    )
                }
        }
    }
}
