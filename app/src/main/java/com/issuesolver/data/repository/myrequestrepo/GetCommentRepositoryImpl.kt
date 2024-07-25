package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentResponse
import retrofit2.Response
import javax.inject.Inject

interface GetCommentRepositoryInterface {
    suspend fun getComment(page: Int, size: Int): Response<CommentResponse>
}


class GetCommentRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService) :
    GetCommentRepositoryInterface {
    override suspend fun getComment(
        page: Int,
        size: Int
    ): Response<CommentResponse> {
        return myRequestService.getComments(page, size)
    }
}