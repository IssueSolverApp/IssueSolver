package com.issuesolver.domain.usecase.newrequestusecase

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.newrequestrepo.GetCategoryRepositoryInterface
import com.issuesolver.domain.entity.networkModel.NewRequest
import com.issuesolver.domain.entity.networkModel.NewRequestResponseBody
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val getCategory: GetCategoryRepositoryInterface) {


    suspend operator fun invoke() = flow {

        emit(Resource.Loading())
        try {
            val response = getCategory.getCategory()
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

    private fun parseErrorResponse(json: String): NewRequestResponseBody? {
        return try {
            val gson = Gson()
            gson.fromJson(json, NewRequestResponseBody::class.java)
        } catch (e: Exception) {
            null
        }
    }
}