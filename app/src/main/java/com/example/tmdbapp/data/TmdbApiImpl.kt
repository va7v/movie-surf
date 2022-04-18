package com.example.tmdbapp.repositories

import com.example.buttomnavigation.BuildConfig
import com.example.tmdbapp.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
const val API_KEY = BuildConfig.API_KEY
const val BASE_URL = BuildConfig.BASE_URL

interface TmdbApi {
    @GET("/3/discover/movie?with_genres=18&primary_release_year=2022")
    // /3/search/movie?&language=en-US&query=movie&page=1&primary_release_year=2022
    suspend fun loadMovies(@Query("api_key") api_key: String): ApiMoviesList
    @GET("/3/discover/movie?sort_by=vote_average.desc") //top_rated > popular
    suspend fun loadTopMovies(@Query("api_key") api_key: String): ApiMoviesList
    @GET("/3/movie/{id}")
    suspend fun getDetails(@Path("id") id : String?, @Query("api_key") api_key: String)
            : ApiMovieDetails
}
object TmdbApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val TmdbApiService: TmdbApi by lazy { retrofit.create(TmdbApi::class.java) }

    suspend fun getDetails(id : String?, api_key: String) : List<Gentre> {
        return withContext(Dispatchers.IO) {
            TmdbApiService.getDetails(id, api_key)
                .genres
                .map { result ->
                    Gentre(
                        result.id,
                        result.name
                    )
/*
MovieDetails(
                        result.original_title,
                        result.overview,
                        result.poster_path,
                        result.release_date,
                        result.vote_average
                    )*/
                }
        }
    }
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
