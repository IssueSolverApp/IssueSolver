package com.issuesolver.data.repository.login

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.OtpTrustResponse
import com.issuesolver.domain.entity.networkModel.login.RequestOtp
import retrofit2.Response
import javax.inject.Inject


interface OtpTrustRepositoryInterface{
    suspend fun otpTrust(otpTrust: RequestOtp): Response<OtpTrustResponse>
}

class OtpTrustRepositoryImpl @Inject constructor(private val loginService: LoginService):
    OtpTrustRepositoryInterface {
    override suspend fun otpTrust(otpTrust: RequestOtp): Response<OtpTrustResponse> {
        return loginService.trustOtp(otpTrust)
    }
}