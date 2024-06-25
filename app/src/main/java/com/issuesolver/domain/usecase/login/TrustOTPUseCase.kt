package com.issuesolver.domain.usecase.login

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.ResetPasswordRepositoryInterface
import com.issuesolver.data.repository.TrustOTPRepositoryInterface
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.TrustOTPRequest
import com.issuesolver.domain.entity.networkModel.TrustOTPResponse
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrustOTPUseCase @Inject constructor(
    private val trustOTPRepository: TrustOTPRepositoryInterface
) {
    suspend operator fun invoke(otpTrust: TrustOTPRequest) = flow {
        emit(Resource.Loading())
        try {
            val response = trustOTPRepository.trustOTP(otpTrust)
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

    private fun parseErrorResponse(json: String): TrustOTPResponse? {
        return try {
            val gson = Gson()
            gson.fromJson(json, TrustOTPResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}