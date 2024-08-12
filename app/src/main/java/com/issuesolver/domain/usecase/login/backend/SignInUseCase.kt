package com.issuesolver.domain.usecase.login.backend

import android.content.SharedPreferences
import com.google.gson.Gson
import com.issuesolver.data.repository.login.SignInRepositoryInterface
import com.issuesolver.domain.entity.networkModel.login.LoginRequest
import com.issuesolver.domain.entity.networkModel.login.LoginResponse
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepositoryInterface: SignInRepositoryInterface,
    private val sharedPreferences: SharedPreferences
) {

    suspend operator fun invoke(signIn: LoginRequest) = flow {
        emit(ResourceSignIn.Loading())
        try {
            val response = signInRepositoryInterface.signIn(signIn)
            if (response.isSuccessful) {
                response.body()?.let {
                    saveTokens(it.data?.accessToken, it.data?.refreshToken)
                    emit(ResourceSignIn.Success(response.body()))
                } ?: emit(ResourceSignIn.Error("Response body is null"))
            } else {
                val errorResponse = response.errorBody()?.string()?.let {
                    parseErrorResponse(it)
                }
                if (response.code() == 409) {
                    emit(ResourceSignIn.Conflict("409"))
                } else {
                    emit(ResourceSignIn.Error(errorResponse?.message ?: "Unknown Error"))
                }

            }
        } catch (e: IOException) {
            emit(ResourceSignIn.Error("Network Error: ${e.localizedMessage}"))
        } catch (e: HttpException) {
            emit(ResourceSignIn.Error("HTTP Error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(ResourceSignIn.Error("Unexpected Error: ${e.localizedMessage}"))
        }
    }

    private fun saveTokens(accessToken: String?, refreshToken: String?) {
        sharedPreferences.edit().apply {
            putString("access_token", accessToken)
            putString("refresh_token", refreshToken)
            apply()
        }
    }

    private fun parseErrorResponse(json: String): LoginResponse? {
        return try {
            val gson = Gson()
            gson.fromJson(json, LoginResponse::class.java)
        } catch (e: Exception) {
            null
        }
    }
}


sealed class ResourceSignIn <T>(var data: T? = null, var message: String? = null,){

    class Success<T>(data: T) : ResourceSignIn<T>(data = data)

    class Error<T>(message: String) : ResourceSignIn<T>(message = message)

    class Loading<T>() : ResourceSignIn<T>()

    class Conflict<T>(message: String): ResourceSignIn<T>(message = message)

}