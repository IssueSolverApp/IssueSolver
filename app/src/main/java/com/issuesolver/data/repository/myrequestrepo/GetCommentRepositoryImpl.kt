package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentResponse
import retrofit2.Response
import javax.inject.Inject

//interface GetCommentRepositoryInterface {
//    suspend fun getComment(page: Int, size: Int, requestId: Int?): Response<CommentResponse>
//}
//
//
//class GetCommentRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService) :
//    GetCommentRepositoryInterface {
//    override suspend fun getComment(
//        page: Int,
//        size: Int,
//        requestId: Int?
//    ): Response<CommentResponse> {
//        return myRequestService.getComments(page, size,requestId)
//    }
//}

interface CommentRepository {
    suspend fun getComments(requestId: Int?, page: Int, size: Int): Response<CommentResponse>
}

class CommentRepositoryImpl @Inject constructor(
    private val apiService: MyRequestService // Ваш сервис Retrofit
) : CommentRepository {
    override suspend fun getComments(requestId: Int?, page: Int, size: Int): Response<CommentResponse> {
        return apiService.getComments(requestId, page, size )
    }
}