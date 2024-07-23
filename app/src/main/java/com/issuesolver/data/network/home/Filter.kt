package com.issuesolver.data.network.home

import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface FilterService {
    @GET("category")
    suspend fun getCategory(): Response<FilterResponseModel>
}

