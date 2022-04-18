package com.example.tmdbapp.data

data class MovieDetails(
    val genres: List<Result>,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)
