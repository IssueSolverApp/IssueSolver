package com.issuesolver.data.repository.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.issuesolver.data.network.home.FilterService
import com.issuesolver.data.pagination.FilterPagingSource
import com.issuesolver.data.pagination.TestPagingSource
import com.issuesolver.domain.entity.networkModel.home.FilterData
import javax.inject.Inject


class TestFilterRepository @Inject constructor(private val filterService: FilterService) {
    fun getFilterResults(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String
    ): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TestPagingSource(filterService, status, categoryName, organizationName, days)
            }
        )
    }
}
