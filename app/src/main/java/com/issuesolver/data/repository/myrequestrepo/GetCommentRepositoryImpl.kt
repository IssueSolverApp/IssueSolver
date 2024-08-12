package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentResponse
import retrofit2.Response
import javax.inject.Inject

interface CommentRepository {
    suspend fun getComments(requestId: Int?, page: Int, size: Int): Response<CommentResponse>
}

class CommentRepositoryImpl @Inject constructor(
    private val apiService: MyRequestService
) : CommentRepository {
    override suspend fun getComments(requestId: Int?, page: Int, size: Int): Response<CommentResponse> {
        return apiService.getComments(requestId, page, size )
    }
}