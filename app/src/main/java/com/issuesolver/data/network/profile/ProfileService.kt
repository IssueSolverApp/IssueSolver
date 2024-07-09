package com.issuesolver.data.network.profile

import com.issuesolver.domain.entity.networkModel.DeleteAccountRequest
import com.issuesolver.domain.entity.networkModel.DeleteAccountResponse
import com.issuesolver.domain.entity.networkModel.GetMeResponse
import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import com.issuesolver.domain.entity.networkModel.OtpTrustResponse
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import com.issuesolver.domain.entity.networkModel.ResetPasswordModel
import com.issuesolver.domain.entity.networkModel.UpdateFullNameRequest
import com.issuesolver.domain.entity.networkModel.UpdateFullNameResponse
import com.issuesolver.domain.entity.networkModel.UpdatePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProfileService {

    @GET("api/Users/getme")
    suspend fun getme(): Response<GetMeResponse>
    @DELETE("api/Users/delete")
    suspend fun delete(@Body request: DeleteAccountRequest): Response<DeleteAccountResponse>
    @PUT("api/Users/updatepassword")
    suspend fun updatepassword(@Body request: UpdatePasswordRequest): Response<UpdatePasswordRequest>
    @PUT("api/Users/updatefullname")
    suspend fun updatefullname(@Body request: UpdateFullNameRequest): Response<UpdateFullNameResponse>

}

