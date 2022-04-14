package com.example.tmdbapp.data

data class ApiMoviesList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)