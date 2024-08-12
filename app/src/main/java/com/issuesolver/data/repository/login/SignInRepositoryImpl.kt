package com.issuesolver.data.repository.login

import android.content.SharedPreferences
import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.LoginRequest
import com.issuesolver.domain.entity.networkModel.login.LoginResponse
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
        val response = loginService.login(login)
        if (response.isSuccessful) {
            val loginResponse = response.body()
            loginResponse?.data?.accessToken?.let {
                sharedPreferences.edit().putString("accessToken", it).apply()
            }
        }
        return response
    }

}