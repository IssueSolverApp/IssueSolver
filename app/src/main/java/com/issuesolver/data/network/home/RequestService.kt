package com.issuesolver.data.network.home

import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestService {
    @GET("request")
    suspend fun filter(@Query("page") page: Int,
                       @Query("size") size: Int,
    ): Response<FilterResponseModel>
}