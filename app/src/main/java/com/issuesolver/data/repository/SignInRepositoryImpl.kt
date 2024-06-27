package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import retrofit2.Response
import javax.inject.Inject

interface SignInRepositoryInterface {
    suspend fun signIn(login: LoginRequest): Response<LoginResponse>
}


class SignInRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : SignInRepositoryInterface {

    override suspend fun signIn(login: LoginRequest): Response<LoginResponse> {
        return loginService.login(login)
    }

}