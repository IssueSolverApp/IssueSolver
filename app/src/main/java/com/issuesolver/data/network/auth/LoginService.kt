package com.issuesolver.data.network.auth

import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface LoginService {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @POST("api/Auths/register")
    suspend fun register(@Body request: RegisterRequestModel): Response<ResponseBody>


    @POST("/api/v1/auth/resend-otp")
    suspend fun resendOtp(@Query("email") email: String?):Response<ResponseBody>

    @POST("/api/v1/auth/confirm-otp")
    suspend fun confirmOtp(@Query("otp") otp: String?):Response<ResponseBody>

}


