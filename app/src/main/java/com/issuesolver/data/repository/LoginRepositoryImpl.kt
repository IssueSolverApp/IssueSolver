package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import okhttp3.Response
import javax.inject.Inject
//
//
//interface LoginRepositoryInterface {
//    suspend fun login(request: LoginRequest): Response<LoginResponse>
//}
//
//
//class LoginRepositoryImpl @Inject constructor(
//    private val loginService: LoginService
//) : LoginRepositoryInterface {
//
//    override suspend fun login(request: LoginRequest): Response<LoginResponse> {
//        return loginService.login(request)
//    }
//}