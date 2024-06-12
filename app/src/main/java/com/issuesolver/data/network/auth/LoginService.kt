package com.issuesolver.data.network.auth

import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginService {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @POST("api/Auths/register")
    suspend fun register(@Body request: RegisterRequestModel): Response<Any>


//    @POST("/api/v1/auth/resend-otp")
//    suspend fun resendOtp()

}


