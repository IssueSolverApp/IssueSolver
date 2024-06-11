package com.issuesolver.data.repository

import com.issuesolver.data.network.LoginService
import com.issuesolver.domain.entity.mapper.LoginRequest
import com.issuesolver.domain.entity.mapper.LoginResponse
import javax.inject.Inject

interface LoginRepositoryInterface {
    suspend fun login(request: LoginRequest): Result<LoginResponse>
}



class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRepositoryInterface {

    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = loginService.login(request)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}