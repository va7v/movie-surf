package com.example.tmdbapp.data.network

data class Movie(
    val id: String?,
    val release_date: String?,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?,
    val overview: String?
)