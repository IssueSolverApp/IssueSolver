package com.issuesolver.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.issuesolver.data.network.home.FilterService
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel

class FilterPagingSource(
    private val filterRepository: FilterRepository,
    private val status: String,
    private val categoryName: String,
    private val organizationName: String,
    private val days: String
) : PagingSource<Int, FilterData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilterData> {
        val page = params.key ?: 1
        return try {
            val response = filterRepository.getFilteredResults(status, categoryName, organizationName, days, page, params.loadSize)
            val items = response.body()?.data ?: emptyList()
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FilterData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

