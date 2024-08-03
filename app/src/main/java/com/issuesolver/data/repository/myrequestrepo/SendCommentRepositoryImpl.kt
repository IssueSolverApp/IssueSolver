package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentResponse
import retrofit2.Response
import javax.inject.Inject

interface SendCommentRepositoryInterface{
    suspend fun sendComment(requestId: Int?, commentText: CommentRequest): Response<CommentResponse>
}


class SendCommentRepositoryImpl@Inject constructor(private val myRequestService: MyRequestService):SendCommentRepositoryInterface {
    override suspend fun sendComment(requestId: Int?, commentText: CommentRequest): Response<CommentResponse> {
        return myRequestService.sendComment(requestId, commentText)
    }


}