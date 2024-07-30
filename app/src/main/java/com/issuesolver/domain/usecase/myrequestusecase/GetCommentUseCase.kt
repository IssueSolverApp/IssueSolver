package com.issuesolver.domain.usecase.myrequestusecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.pagination.CommentsPagingSource
import com.issuesolver.data.pagination.FilterPagingSource
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.myrequestrepo.CommentRepository
import com.issuesolver.data.repository.myrequestrepo.GetRequestByIdRepositoryInterface
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.w3c.dom.Comment
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//class GetCommentUseCase@Inject constructor(private val myRequest: GetCommentRepositoryInterface)  {
//
//
//    suspend operator fun invoke( page:Int, size:Int) = flow {
//
//        emit(Resource.Loading())
//        try {
//            val response = myRequest.getComment(page, size)
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
//
//}




//class GetCommentUseCase@Inject constructor(private val getCommentsRepository: GetCommentRepositoryInterface) {
//
//    fun execute(
//        requestId: Int?
//    ): Pager<Int, CommentData> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = {
//                GetCommentPagination(getCommentsRepository, requestId)
//            }
//        )
//    }
//}



class GetCommentsUseCase @Inject constructor(
    private val repository: CommentRepository
) {
    operator fun invoke(requestId: Int?): Flow<PagingData<CommentData>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { CommentsPagingSource(repository, requestId) }
        ).flow
    }
}
