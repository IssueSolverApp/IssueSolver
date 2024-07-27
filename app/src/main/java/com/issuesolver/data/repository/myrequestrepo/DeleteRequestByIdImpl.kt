package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.home.LikeResponse
import retrofit2.Response
import javax.inject.Inject

interface DeleteRequestByIdInterface{
    suspend fun deleteRequestById(requestId: Int): Response<LikeResponse>
}


class DeleteRequestByIdImpl@Inject constructor(private val myRequestService: MyRequestService):DeleteRequestByIdInterface {
    override suspend fun deleteRequestById(requestId: Int): Response<LikeResponse> {
        return myRequestService.deleteRequestById(requestId)
    }
}