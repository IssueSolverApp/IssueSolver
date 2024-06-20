package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

interface ResendOtpRepositoryInterface{
    suspend fun resendOtp(resendOtpModel: ResendOtpModel): Response<ResponseBody>
}

class ResendOtpRepositoryImpl @Inject constructor(private val loginService: LoginService):ResendOtpRepositoryInterface {
    override suspend fun resendOtp(resendOtpModel: ResendOtpModel): Response<ResponseBody> {
        return loginService.resendOtp(resendOtpModel.email)
    }

}