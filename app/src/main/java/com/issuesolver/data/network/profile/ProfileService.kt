package com.issuesolver.data.network.profile

import com.issuesolver.domain.entity.networkModel.profile.DeleteAccountRequest
import com.issuesolver.domain.entity.networkModel.profile.GetMeResponse
import com.issuesolver.domain.entity.networkModel.profile.ProfileResponse
import com.issuesolver.domain.entity.networkModel.profile.UpdateFullNameRequest
import com.issuesolver.domain.entity.networkModel.profile.UpdatePasswordRequest
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

