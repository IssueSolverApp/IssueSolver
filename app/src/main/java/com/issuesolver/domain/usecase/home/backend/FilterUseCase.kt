package com.issuesolver.domain.usecase.home.backend

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.pagination.FilterPagingSource
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.home.RequestInterface
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//class FilterUseCase @Inject constructor(private val filter: FilterInterface) {
//
//
//    suspend operator fun invoke( status: String,
//                                 categoryName: String,
//                                 organizationName: String,
//                                 days: String) = flow {
//
//        emit(Resource.Loading())
//        try {
//            val response = filter.filter( status,
//                categoryName,
//                organizationName,
//                days)
//            if (response.isSuccessful) {
//                emit(Resource.Success(response.body()))
//            } else {
//                val errorResponse = response.errorBody()?.string()?.let {
//                    parseErrorResponse(it)
//                }
//                emit(Resource.Error(errorResponse?.message ?: "Unknown Error"))
//            }
//        } catch (e: IOException) {
//            emit(Resource.Error("Network Error: ${e.localizedMessage}"))
//        } catch (e: HttpException) {
//            emit(Resource.Error("HTTP Error: ${e.localizedMessage}"))
//        } catch (e: Exception) {
//            emit(Resource.Error("Unexpected Error: ${e.localizedMessage}"))
//        }
//    }
//
//    private fun parseErrorResponse(json: String): FilterResponseModel? {
//        // Use your preferred JSON library here (e.g., Gson)
//        // Assuming you're using Gson:
//        return try {
//            val gson = Gson()
//            gson.fromJson(json, FilterResponseModel::class.java)
//        } catch (e: Exception) {
//            null
//        }
//    }
//}
/*
class FilterUseCase @Inject constructor(
    private val filter: FilterInterface
) {
    operator fun invoke(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String
    ): Flow<PagingData<FilterData>> {
        return filter.filter(status, categoryName, organizationName, days).flow
    }
}*/


class GetFilteredResultsUseCase@Inject constructor(private val filterRepository: FilterRepository) {

    fun execute(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String
    ): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                FilterPagingSource(filterRepository, status, categoryName, organizationName, days)
            }
        )
    }
}
