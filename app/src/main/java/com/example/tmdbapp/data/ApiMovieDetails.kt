package com.example.tmdbapp.data
//постер, название, дата выхода, рейтинг, жанр..., описание, список актеров - credits
data class ApiMovieDetails(
    val genres: List<genres>,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double
)

data class genres(
    val id: String,
    val name: String
)
