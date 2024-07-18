package com.issuesolver.domain.usecase.newrequestusecase

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.newrequestrepo.NewRequestRepositoryInterface
import com.issuesolver.domain.entity.networkModel.NewRequest
import com.issuesolver.domain.entity.networkModel.NewRequestResponseBody
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.RequestOtp
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewRequestUseCase@Inject constructor(private val newRequest: NewRequestRepositoryInterface) {


    suspend operator fun invoke(categoryName: String,createNewRequest: NewRequest) = flow {

        emit(Resource.Loading())
        try {
            val response = newRequest.newRequest(categoryName, createNewRequest)
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
    private fun parseErrorResponse(json: String): NewRequestResponseBody? {
        // Use your preferred JSON library here (e.g., Gson)
        // Assuming you're using Gson:
        return try {
            val gson = Gson()
            gson.fromJson(json, NewRequestResponseBody::class.java)
        } catch (e: Exception) {
            null
        }
    }
}