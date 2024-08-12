package com.issuesolver.domain.usecase.profile.backend

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.profile.UpdateFullNameRepositoryInterFace
import com.issuesolver.domain.entity.networkModel.login.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.profile.UpdateFullNameRequest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateFullNameUseCase @Inject constructor(private val updateFullNameRepository: UpdateFullNameRepositoryInterFace) {

    suspend operator fun invoke(fullname: UpdateFullNameRequest) = flow {

        emit(Resource.Loading())
        try {
            val response = updateFullNameRepository.updateFullName(fullname)
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