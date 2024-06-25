package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.ForgetPasswordRequest
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import retrofit2.Response
import javax.inject.Inject


interface ForgetPasswordRepositoryInterface{
    suspend fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest): Response<RegisterResponseModel>
}

class ForgetPasswordRepositoryImpl @Inject constructor(private val loginService: LoginService): ForgetPasswordRepositoryInterface {

    override suspend fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest): Response<RegisterResponseModel> {
        return loginService.forgetPassword(forgetPasswordRequest)
    }


}