package com.example.tmdbapp.data

data class ApiMovieDetails(
    val genres: List<genres>,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)

data class genres(
    val name: String
)
