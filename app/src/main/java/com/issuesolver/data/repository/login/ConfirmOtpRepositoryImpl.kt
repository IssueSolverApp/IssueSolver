package com.issuesolver.data.repository.login

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.RequestOtp
import retrofit2.Response
import javax.inject.Inject

interface ConfirmOtpRepositoryInterface {
    suspend fun confirmOtp(requestOtp: RequestOtp): Response<RegisterResponseModel>
}

class ConfirmOtpRepositoryImpl @Inject constructor(private val loginService: LoginService) :
    ConfirmOtpRepositoryInterface {
    override suspend fun confirmOtp(requestOtp: RequestOtp): Response<RegisterResponseModel> {
        return loginService.confirmOtp(requestOtp)
    }
}