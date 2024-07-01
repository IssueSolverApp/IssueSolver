package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

interface ResendOtpRepositoryInterface{
    suspend fun resendOtp(resendOtpModel: ResendOtpModel): Response<RegisterResponseModel>
}

class ResendOtpRepositoryImpl @Inject constructor(private val loginService: LoginService):ResendOtpRepositoryInterface {
    override suspend fun resendOtp(resendOtpModel: ResendOtpModel): Response<RegisterResponseModel> {
        return loginService.resendOtp(resendOtpModel)
    }

}