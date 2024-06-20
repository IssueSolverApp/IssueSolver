package com.issuesolver.data.repository

import com.issuesolver.common.Resource
import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.domain.entity.networkModel.RegisterErrorResponseModel
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

interface RegisterRepositoryInterface{
    suspend fun createUser(registerRequestModel: RegisterRequestModel): Response<RegisterErrorResponseModel>
}

class RegisterRepositoryImpl @Inject constructor(private val loginService: LoginService): RegisterRepositoryInterface {


        override suspend fun createUser(registerRequestModel: RegisterRequestModel): Response<RegisterErrorResponseModel>{

//            val response = loginService.register(registerRequestModel)
//
//            if (response.isSuccessful){
//                val responseBody = response.body()?.toString()?: ""
//                Resource.Success(responseBody)
//            }
            return loginService.register(registerRequestModel)

        }
            //emit(loginService.register(registerRequestModel))


//        {
//            return loginService.register(registerRequestModel)
//        }
    }

