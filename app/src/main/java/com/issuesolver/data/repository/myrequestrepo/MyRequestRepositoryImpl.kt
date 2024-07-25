package com.issuesolver.data.repository.myrequestrepo

import com.issuesolver.data.network.home.RequestService
import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject

interface MyRequestRepositoryInterface{
    suspend fun myRequest(page:Int, size:Int): Response<FilterResponseModel>
}

class MyRequestRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService):MyRequestRepositoryInterface {
    override suspend fun myRequest(page: Int, size: Int): Response<FilterResponseModel> {
        return myRequestService.myRequest(page, size)
    }
}