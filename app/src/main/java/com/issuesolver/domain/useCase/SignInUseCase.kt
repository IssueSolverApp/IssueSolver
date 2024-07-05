package com.issuesolver.domain.usecase

import android.content.SharedPreferences
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.SignInRepositoryInterface
import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val signInRepositoryInterface: SignInRepositoryInterface,
                                        private val sharedPreferences: SharedPreferences
) {

    suspend operator fun invoke(signIn: LoginRequest) = flow {
        emit(Resource.Loading())
        try {
            val response = signInRepositoryInterface.signIn(signIn)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
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

    private fun parseErrorResponse(json: String): LoginResponse? {
        // Use your preferred JSON library here (e.g., Gson)
        // Assuming you're using Gson:
        return try {
            val gson = Gson()
            gson.fromJson(json, LoginResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}