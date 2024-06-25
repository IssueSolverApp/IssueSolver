package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.ResetPasswordRequest
import retrofit2.Response
import javax.inject.Inject


interface ResetPasswordRepositoryInterface{
    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Response<RegisterResponseModel>
}

class ResetPasswordRepositoryImpl @Inject constructor(private val token: String? , private val loginService: LoginService): ResetPasswordRepositoryInterface {



    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Response<RegisterResponseModel> {
        return loginService.resetPassword(token, resetPasswordRequest)
    }


}