package com.issuesolver.data.network.auth

import com.issuesolver.domain.entity.networkModel.login.LoginRequest
import com.issuesolver.domain.entity.networkModel.login.LoginResponse
import com.issuesolver.domain.entity.networkModel.login.OtpTrustResponse
import com.issuesolver.domain.entity.networkModel.login.RefreshTokenRequest
import com.issuesolver.domain.entity.networkModel.login.RefreshTokenResponse
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.RegisterRequestModel
import com.issuesolver.domain.entity.networkModel.login.RequestOtp
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import com.issuesolver.domain.entity.networkModel.login.ResetPasswordModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface LoginService {
    @POST("api/Auths/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

//    @POST()
//    suspend fun loginv2(@Url url: String = , @Body request: LoginRequest): Response<LoginResponse>

    @POST("api/Auths/register")
    suspend fun register(@Body request: RegisterRequestModel): Response<RegisterResponseModel>

    @POST("api/Auths/resend-otp")
    suspend fun resendOtp(@Body email: ResendOtpModel?): Response<RegisterResponseModel>

    @POST("api/Auths/confirm-otp")
    suspend fun confirmOtp(@Body otp: RequestOtp?): Response<RegisterResponseModel>

    @POST("api/Auths/forget-password")
    suspend fun forgetPassword(@Body email: ResendOtpModel?): Response<RegisterResponseModel>

    @POST("api/Auths/otp-trust")
    suspend fun trustOtp(@Body otp: RequestOtp): Response<OtpTrustResponse>

    @POST("api/Auths/reset-password")
    suspend fun resetPassword(@Query("token") token: String, @Body resetPassword: ResetPasswordModel): Response<RegisterResponseModel>

    @POST("api/Auths/login-refreshtoken")
    suspend fun refreshToken(@Body token: RefreshTokenRequest): Response<RefreshTokenResponse>

}


