package com.issuesolver.domain.useCase

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.RegisterRepositoryInterface
import com.issuesolver.data.repository.ResendOtpRepositoryInterface
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


/*
class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepositoryInterface) {
    suspend operator fun invoke(user: RegisterRequestModel): Flow<Resource<String>> = flow {
        emit(Resource.Loading)
    }


//    fun execute(registerRequestModel: RegisterRequestModel) = flow {
//        emit(Resource.Loading())
//        registerRepository.createUser(registerRequestModel)?.let {
//            emit(Resource.Success(it))
//        } ?: emit(Resource.Error(message = ))
//    }



}*/

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepositoryInterface
) {
    suspend operator fun invoke(register: RegisterRequestModel) = flow {

        try {
            val response = registerRepository.createUser(register)
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


//    = flow {
//        emit(Resource.Loading())
//        registerRepository.createUser(user).let {
//            emit(Resource.Success(it))
//        }?: emit(Resource.Error())
//    }.catch {
//
//    }
//        return try {
//            val response = registerRepository.createUser(user)
//            if (response.isSuccessful) {
//                Result.success(Unit)
//            } else {
//                Result.failure(Exception("Failed to create user"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }



