package com.issuesolver.data.repository

import android.content.SharedPreferences
import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import retrofit2.Response
import javax.inject.Inject

interface SignInRepositoryInterface {
    suspend fun signIn(login: LoginRequest): Response<LoginResponse>
}


class SignInRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val sharedPreferences: SharedPreferences
) : SignInRepositoryInterface {

    override suspend fun signIn(login: LoginRequest): Response<LoginResponse> {
        //return loginService.login(login)
        val response = loginService.login(login)
        if (response.isSuccessful) {
            val loginResponse = response.body()
            loginResponse?.data?.accessToken?.let {
                sharedPreferences.edit().putString("accessToken", it).apply()
            }
        }
        return response
    }

    suspend fun refreshAccessToken(): Response<LoginResponse> {
        val refreshToken = sharedPreferences.getString("refreshToken", null)
        // Call your refresh token endpoint here
        // Example:
        return loginService.refreshToken(refreshToken)

    }

}