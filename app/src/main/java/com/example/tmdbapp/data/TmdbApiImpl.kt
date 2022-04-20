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
    @GET("/3/discover/movie?primary_release_year=2022") //with_genres=18&
    // /3/search/movie?&language=en-US&query=movie&page=1&primary_release_year=2022
    suspend fun loadMovies(@Query("api_key") api_key: String): ApiMoviesList
    @GET("/3/discover/movie?sort_by=vote_average.desc") //top_rated > popular
    suspend fun loadTopMovies(@Query("api_key") api_key: String): ApiMoviesList
    @GET("/3/movie/{id}")
    suspend fun getDetails(@Path("id") id : String?, @Query("api_key") api_key: String)
            : ApiMovieDetails
    @GET("/3/movie/{id}/credits")
    suspend fun getActors(@Path("id") id : String?, @Query("api_key") api_key: String)
            : ApiMovieCredits
}
object TmdbApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val TmdbApiService: TmdbApi by lazy { retrofit.create(TmdbApi::class.java) }

    suspend fun getActors(id : String?, api_key: String) : List<Actor> {
        return withContext(Dispatchers.IO) {
            TmdbApiService.getActors(id, api_key)
                .cast
                .filter { it.character != null }
                .map { result ->
                    Actor(
                        result.id,
                        result.name,
                        result.character,
                        result.profile_path
                    )
                }
        }
    }
    suspend fun getDetails(id : String?, api_key: String) : List<Gentre> {//
        return withContext(Dispatchers.IO) {
            TmdbApiService.getDetails(id, api_key)
                .genres
                .map { result ->
                    Gentre(
                        result.name
                    )
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
                        result.vote_average,
                        result.overview
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
                        result.vote_average,
                        result.overview
                    )
                }
        }
    }
}
