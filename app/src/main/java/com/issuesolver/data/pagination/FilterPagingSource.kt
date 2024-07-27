package com.issuesolver.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.issuesolver.data.network.home.FilterService
import com.issuesolver.domain.entity.networkModel.home.FilterData

class FilterPagingSource(
    private val filterService: FilterService,
    private val status: String,
    private val categoryName: String,
    private val organizationName: String,
    private val days: String
) : PagingSource<Int, FilterData>() {
    override fun getRefreshKey(state: PagingState<Int, FilterData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilterData> {
        val page = params.key ?: 0
        return try {
            val response = filterService.filter(
                status,
                categoryName,
                organizationName,
                days,
                page,
                params.loadSize
            )
            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                LoadResult.Page(
                    data = data,
                    prevKey = null,
//                    if (page == 1) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
