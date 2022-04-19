package com.example.tmdbapp.data

data class ApiMovieCredits(
    val page: Int,
    val cast: List<casts>
)

data class casts(
    val id: String,
    val name: String,
    val character: String,
    val profile_path: String
)
