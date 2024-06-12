package com.issuesolver.data.repository

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import retrofit2.Response
import javax.inject.Inject

interface RegisterRepositoryInterface{
    suspend fun createUser(registerRequestModel: RegisterRequestModel): Response<Any>
}

class RegisterRepositoryImpl @Inject constructor(private val loginService: LoginService): RegisterRepositoryInterface {


        override suspend fun createUser(registerRequestModel: RegisterRequestModel): Response<Any> {
            return loginService.register(registerRequestModel)
        }
    }


