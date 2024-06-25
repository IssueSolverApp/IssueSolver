package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.ForgetPasswordRequest
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.TrustOTPRequest
import com.issuesolver.domain.entity.networkModel.TrustOTPResponse
import retrofit2.Response
import javax.inject.Inject

interface TrustOTPRepositoryInterface{
    suspend fun trustOTP(trustOTPRequest: TrustOTPRequest): Response<TrustOTPResponse>
}

class TrustOTPRepositoryImpl @Inject constructor(private val loginService: LoginService): TrustOTPRepositoryInterface {

    override suspend fun trustOTP(trustOTPRequest: TrustOTPRequest): Response<TrustOTPResponse> {
        return loginService.otpTrust(trustOTPRequest)
    }


}