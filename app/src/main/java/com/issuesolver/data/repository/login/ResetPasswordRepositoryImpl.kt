package com.issuesolver.data.repository.login

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.ResetPasswordModel
import retrofit2.Response
import javax.inject.Inject

interface ResetPasswordRepositoryInterface {
    suspend fun resetPassword(
        token: String,
        resetPasswordModel: ResetPasswordModel
    ): Response<RegisterResponseModel>
}

class ResetPasswordRepositoryImpl @Inject constructor(private val loginService: LoginService)
    : ResetPasswordRepositoryInterface {
    override suspend fun resetPassword(
        token: String,
        resetPasswordModel: ResetPasswordModel
    ): Response<RegisterResponseModel> {
        return loginService.resetPassword(token, resetPasswordModel)
    }

}