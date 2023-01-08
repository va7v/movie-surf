package com.example.tmdbapp.data

import com.example.tmdbapp.data.network.Movie

data class ApiTVShowList(
    val page: Int,
    val results: List<ResultTS>,
    val total_pages: Int,
    val total_results: Int
)

data class ResultTS(

    val id: String?,

    val overview: String?,

    val poster_path: String?,
    val first_air_date: String?,
    val name: String?,

    val vote_average: Double?,
) {
    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = name,
            release_date = first_air_date,
            poster_path = poster_path,
            vote_average = vote_average,
            overview = overview
        )
    }
}