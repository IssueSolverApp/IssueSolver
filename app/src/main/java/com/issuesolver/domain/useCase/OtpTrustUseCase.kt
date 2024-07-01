package com.issuesolver.domain.useCase

import android.content.SharedPreferences
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.OtpTrustRepositoryInterface
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OtpTrustUseCase @Inject constructor(
    private val otpTrustRepository: OtpTrustRepositoryInterface,
    private val sharedPreferences: SharedPreferences
) {

    suspend operator fun invoke(otpModel: RequestOtp) = flow {
        emit(Resource.Loading())

        try {
            val response = otpTrustRepository.otpTrust(otpModel)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.message))
                val token = response.body()?.data
                token.let {
                    saveToken(it.toString())
                }
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

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    private fun parseErrorResponse(json: String): RegisterResponseModel? {
        // Use your preferred JSON library here (e.g., Gson)
        // Assuming you're using Gson:
        return try {
            val gson = Gson()
            gson.fromJson(json, RegisterResponseModel::class.java)
        } catch (e: Exception) {
            null
        }
    }

}