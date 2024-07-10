package com.issuesolver.data.repository.login

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import retrofit2.Response
import javax.inject.Inject

interface ResendOtpRepositoryInterface{
    suspend fun resendOtp(resendOtpModel: ResendOtpModel): Response<RegisterResponseModel>
}

class ResendOtpRepositoryImpl @Inject constructor(private val loginService: LoginService):
    ResendOtpRepositoryInterface {
    override suspend fun resendOtp(resendOtpModel: ResendOtpModel): Response<RegisterResponseModel> {
        return loginService.resendOtp(resendOtpModel)
    }

}