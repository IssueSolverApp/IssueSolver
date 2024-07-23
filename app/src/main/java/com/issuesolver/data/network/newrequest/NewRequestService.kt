package com.issuesolver.data.network.newrequest

import com.issuesolver.domain.entity.networkModel.NewRequest
import com.issuesolver.domain.entity.networkModel.NewRequestResponseBody
import com.issuesolver.domain.entity.networkModel.category.GetCategoryResponseModel
import com.issuesolver.domain.entity.networkModel.organization.GetOrganizationResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewRequestService {

    @POST("request")
    suspend fun newRequest(@Query("categoryName") categoryName: String, @Body request: NewRequest): Response<NewRequestResponseBody>


    @GET("category")
    suspend fun getCategory(): Response<GetCategoryResponseModel>



    @GET("api/Organizations/getlist")
    suspend fun getOrganization(): Response<GetOrganizationResponseModel>




    //api/Organizations/getlist

}