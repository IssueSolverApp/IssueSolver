package com.issuesolver.data.network.auth

import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.entity.networkModel.RequestOtp
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url


interface LoginService {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

//    @POST()
//    suspend fun loginv2(@Url url: String = , @Body request: LoginRequest): Response<LoginResponse>

    @POST("api/Auths/register")
    suspend fun register(@Body request: RegisterRequestModel): Response<RegisterResponseModel>

    @POST("/api/v1/auth/resend-otp")
    suspend fun resendOtp(@Query("email") email: String?):Response<ResponseBody>

    @POST("api/Auths/confirm-otp")
    suspend fun confirmOtp(@Query("otp") otp: RequestOtp?):Response<RegisterResponseModel>

}


