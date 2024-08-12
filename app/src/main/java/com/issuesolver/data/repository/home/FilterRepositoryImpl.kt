package com.issuesolver.data.repository.home

import com.issuesolver.data.network.home.FilterService
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response


class FilterRepository(private val filterService: FilterService) {

    suspend fun getFilteredResults(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String,
        page: Int,
        size: Int
    ): Response<FilterResponseModel> {
        return filterService.filter(status, categoryName, organizationName, days, page, size)
    }
}
