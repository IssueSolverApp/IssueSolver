package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.OtpTrustResponse
import com.issuesolver.domain.entity.networkModel.RequestOtp
import retrofit2.Response
import javax.inject.Inject


interface OtpTrustRepositoryInterface{
    suspend fun otpTrust(login: RequestOtp): Response<OtpTrustResponse>
}

class OtpTrustRepositoryImpl @Inject constructor(private val loginService: LoginService): OtpTrustRepositoryInterface {
    override suspend fun otpTrust(otpTrust: RequestOtp): Response<OtpTrustResponse> {
        return loginService.trustOtp(otpTrust)
    }
}