package com.example.tmdbapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.buttomnavigation.BuildConfig
import com.example.tmdbapp.data.*
import com.example.tmdbapp.data.model.Actor
import com.example.tmdbapp.data.model.Gentre
import com.example.tmdbapp.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow

const val API_KEY = BuildConfig.API_KEY
const val BASE_URL = BuildConfig.BASE_URL

interface TmdbApi {
    @GET("/3/discover/movie?primary_release_year=2022") //with_genres=18&
    // /3/search/movie?&language=en-US&query=movie&page=1&primary_release_year=2022
    suspend fun loadMovies(@Query("api_key") api_key: String): ApiMoviesList

    @GET("/3/movie/top_rated") //~ popular discover/movie?sort_by=vote_average.desc
    suspend fun loadTopMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int = 1
    ): ApiMoviesList

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
    
    fun getPagedMovies(): Flow<PagingData<Movie>> {
        val loader: MoviesPageLoader = { pageIndex -> loadTopMovies(pageIndex) }
        return Pager(
            config = PagingConfig( pageSize = 1,  enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(loader) }
        ).flow
    }

    suspend fun loadTopMovies(pageIndex: Int): List<Movie>
    = withContext(Dispatchers.IO) {
        val movies = TmdbApiService.loadTopMovies(API_KEY, pageIndex)
        return@withContext movies.results
            .map  { result ->
                Movie(
                    result.id,
                    result.release_date,
                    result.title,
                    result.poster_path,
                    result.vote_average,
                    result.overview
                )
            }
            //(ApiMoviesList::toMovie)
    }

/*    suspend fun loadTopMovies() : List<Movie> {
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
    }*/
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

}
