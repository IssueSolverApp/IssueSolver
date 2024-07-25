package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject

interface GetRequestByIdRepositoryInterface {
    suspend fun myRequest(requestId: Int): Response<FilterResponseModel>
}


class GetRequestByIdRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService):GetRequestByIdRepositoryInterface {
    override suspend fun myRequest(requestId: Int): Response<FilterResponseModel> {
        return myRequestService.requestById(requestId)
    }

}