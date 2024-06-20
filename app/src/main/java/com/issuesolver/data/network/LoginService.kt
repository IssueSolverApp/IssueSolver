package com.issuesolver.data.network

import com.issuesolver.domain.entity.mapper.LoginRequest
import com.issuesolver.domain.entity.mapper.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}