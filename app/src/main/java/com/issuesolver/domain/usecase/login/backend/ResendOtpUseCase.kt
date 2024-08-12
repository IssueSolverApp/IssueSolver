package com.issuesolver.domain.usecase.login.backend

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.login.ResendOtpRepositoryInterface
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ResendOtpUseCase @Inject constructor(private val resendOtpRepository: ResendOtpRepositoryInterface) {

    suspend operator fun invoke(otpModel: ResendOtpModel) = flow {
        try {
            val response = resendOtpRepository.resendOtp(otpModel)
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