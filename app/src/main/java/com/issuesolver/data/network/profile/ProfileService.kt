package com.issuesolver.data.network.profile

import com.issuesolver.domain.entity.networkModel.DeleteAccountRequest
import com.issuesolver.domain.entity.networkModel.GetMeResponse
import com.issuesolver.domain.entity.networkModel.ProfileResponse
import com.issuesolver.domain.entity.networkModel.UpdateFullNameRequest
import com.issuesolver.domain.entity.networkModel.UpdatePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface  ProfileService {

    @GET("api/Users/getme")
    suspend fun getme(): Response<GetMeResponse>
    @DELETE("api/Users/delete")
    suspend fun delete(@Body request: DeleteAccountRequest): Response<ProfileResponse>
    @PUT("api/Users/updatepassword")
    suspend fun updatepassword(@Body request: UpdatePasswordRequest): Response<ProfileResponse>
    @PUT("api/Users/updatefullname")
    suspend fun updatefullname(@Body request: UpdateFullNameRequest): Response<ProfileResponse>

}

