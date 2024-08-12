package com.issuesolver.domain.usecase.myrequestusecase

import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.myrequestrepo.DeleteRequestByIdInterface
import com.issuesolver.data.repository.myrequestrepo.LikeRepositoryInterface
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteRequestByIdUseCase @Inject constructor(private val myRequest: DeleteRequestByIdInterface) {

    suspend operator fun invoke( requestId: Int) = flow {

        emit(Resource.Loading())
        try {
            val response = myRequest.deleteRequestById(requestId)
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

    private fun parseErrorResponse(json: String): FilterResponseModel? {
        return try {
            val gson = Gson()
            gson.fromJson(json, FilterResponseModel::class.java)
        } catch (e: Exception) {
            null
        }
    }
}