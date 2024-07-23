package com.issuesolver.data.repository.home

import com.issuesolver.data.network.home.FilterService
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject



interface FilterInterface{
    suspend fun getCategory(): Response<FilterResponseModel>
}
class FilterRepositoryImpl @Inject constructor(private val filterService: FilterService):
    FilterInterface {
    override suspend fun getCategory(): Response<FilterResponseModel> {
        return filterService.getCategory()
    }
}