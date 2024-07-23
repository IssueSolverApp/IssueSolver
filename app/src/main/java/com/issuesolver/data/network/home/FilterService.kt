package com.issuesolver.data.network.home

import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.ResetPasswordModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FilterService {
    @GET("category")
    suspend fun getCategory(@Query("status") status: String,
                            @Query("categoryName") categoryName: String,
                            @Query("organizationName") organizationName: String,
                            @Query("days") days: String,
                             ): Response<FilterResponseModel>
}

