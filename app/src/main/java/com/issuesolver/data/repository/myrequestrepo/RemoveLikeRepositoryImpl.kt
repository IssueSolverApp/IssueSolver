package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.home.LikeResponse
import retrofit2.Response
import javax.inject.Inject

interface RemoveLikeRepositoryInterface{
    suspend fun removeLike(requestId: Int): Response<LikeResponse>
}

class RemoveLikeRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService):RemoveLikeRepositoryInterface {
    override suspend fun removeLike(requestId: Int): Response<LikeResponse> {
            return myRequestService.removeLike(requestId)
    }
}