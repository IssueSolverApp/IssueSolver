package com.issuesolver.data.repository.home

import com.issuesolver.data.network.home.RequestService
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject

interface RequestInterface{
    suspend fun request(page:Int, size:Int): Response<FilterResponseModel>
}
class RequestRepositoryImpl @Inject constructor(private val requestService: RequestService):RequestInterface{
    override suspend fun request(page: Int, size: Int): Response<FilterResponseModel> {
        return requestService.filter( page,size)
    }

}