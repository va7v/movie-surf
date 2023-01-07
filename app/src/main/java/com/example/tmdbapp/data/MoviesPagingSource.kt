package com.example.tmdbapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbapp.data.network.Movie
import java.lang.Exception


typealias MoviesPageLoader = suspend (pageIndex: Int) -> List<Movie>

@Suppress("UnnecessaryVariable")
class MoviesPagingSource(
    private val loader: MoviesPageLoader//, private val pageSize: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: 1
        return try {
            val movies = loader.invoke(pageNumber)
            val nextPageNumber = if (movies.isEmpty()) null else pageNumber + 1
            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
            return LoadResult.Page(movies, prevPageNumber, nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

}
