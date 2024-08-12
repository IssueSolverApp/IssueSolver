package com.issuesolver.domain.usecase.login.backend

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.login.ForgetPasswordRepositoryInterface
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(private val forgetPasswordRepository: ForgetPasswordRepositoryInterface) {

    suspend operator fun invoke(signIn: ResendOtpModel) = flow {
        emit(Resource.Loading())
        try {
            val response = forgetPasswordRepository.forgetPassword(signIn)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.message))
            } else {
                val errorResponse = response.errorBody()?.string()?.let {
                    parseErrorResponse(it)
                }
                emit(Resource.Error(errorResponse?.message ?: "Unknown Error"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Network Error: ${e.localizedMessage}"))
        } catch (e: HttpException) {
            emit(Resource.Error("HTTP Error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(Resource.Error("Unexpected Error: ${e.localizedMessage}"))
        }
    }
    private fun parseErrorResponse(json: String): RegisterResponseModel? {
        return try {
            val gson = Gson()
            gson.fromJson(json, RegisterResponseModel::class.java)
        } catch (e: Exception) {
            null
        }
    }
}