package com.example.tmdbapp.repositories

import com.example.buttomnavigation.BuildConfig
import com.example.tmdbapp.data.Movie
import com.example.tmdbapp.data.ApiMoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
const val API_KEY = BuildConfig.API_KEY
const val BASE_URL = BuildConfig.BASE_URL

interface TmdbApi {
    @GET("/3/discover/movie?with_genres=18&primary_release_year=2014")
    // /3/search/movie?&language=en-US&query=movie&page=1&primary_release_year=2022
    suspend fun loadMovies(@Query("api_key") api_key: String): ApiMoviesList
    @GET("/3/discover/movie?sort_by=vote_average.desc")
    ///3/movie/top_rated  vs popular primary_release_year=2022&
    suspend fun loadTopMovies(@Query("api_key") api_key: String): ApiMoviesList
    //@GET("/3/movie/{id}")   getDetails(@Path("id") id : String, @Query("api_key") api_key: String)
}
object TmdbApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val TmdbApiService: TmdbApi by lazy { retrofit.create(TmdbApi::class.java) }

    suspend fun loadMovies() : List<Movie> {
        return withContext(Dispatchers.IO) {
            TmdbApiService.loadMovies(API_KEY)
                .results
                .map { result ->
                    Movie(
                        result.id,
                        result.release_date,
                        result.title,
                        result.poster_path,
                        result.vote_average
                    )
                }
        }
    }
    suspend fun loadTopMovies() : List<Movie> {
        return withContext(Dispatchers.IO) {
            TmdbApiService.loadTopMovies(API_KEY)
                .results
                .map { result ->
                    Movie(
                        result.id,
                        result.release_date,
                        result.title,
                        result.poster_path,
                        result.vote_average
                    )
                }
        }
    }
}
