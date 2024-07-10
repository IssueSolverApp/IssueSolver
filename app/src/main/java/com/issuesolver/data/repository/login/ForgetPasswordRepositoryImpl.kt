package com.issuesolver.data.repository.login

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import retrofit2.Response
import javax.inject.Inject

interface ForgetPasswordRepositoryInterface {
    suspend fun forgetPassword(forgetPassword: ResendOtpModel): Response<RegisterResponseModel>

}


class ForgetPasswordRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): ForgetPasswordRepositoryInterface {

    override suspend fun forgetPassword(forgetPassword: ResendOtpModel): Response<RegisterResponseModel> {
        return loginService.forgetPassword(forgetPassword)
    }


}