package com.example.tmdbapp.data

import com.example.tmdbapp.data.network.Movie

data class ApiMoviesList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Result(

    val id: String?,

    val overview: String?,

    val poster_path: String?,
    val release_date: String?,
    val title: String?,

    val vote_average: Double?,

) {
    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            release_date = release_date,
            poster_path = poster_path,
            vote_average = vote_average,
            overview = overview
        )
    }

}
/*
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>,

    val original_language: String,
    val original_title: String,

    val popularity: Double,

    val video: Boolean,

    val vote_count: Int
 */