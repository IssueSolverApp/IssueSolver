package com.issuesolver.data.repository.login

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.RegisterRequestModel
import retrofit2.Response
import javax.inject.Inject

interface RegisterRepositoryInterface{
    suspend fun createUser(registerRequestModel: RegisterRequestModel): Response<RegisterResponseModel>
}

class RegisterRepositoryImpl @Inject constructor(private val loginService: LoginService):
    RegisterRepositoryInterface {


        override suspend fun createUser(registerRequestModel: RegisterRequestModel): Response<RegisterResponseModel>{

            return loginService.register(registerRequestModel)

        }
    }


