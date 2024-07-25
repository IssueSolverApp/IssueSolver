package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import com.issuesolver.domain.entity.networkModel.home.LikeResponse
import retrofit2.Response
import javax.inject.Inject


interface LikeRepositoryInterface{
    suspend fun like(requestId: Int): Response<LikeResponse>
}

class LikeRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService):LikeRepositoryInterface {
    override suspend fun like(requestId: Int): Response<LikeResponse> {
        return myRequestService.like(requestId)
    }
}