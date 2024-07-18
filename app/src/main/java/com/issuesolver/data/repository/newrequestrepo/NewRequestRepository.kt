package com.issuesolver.data.repository.newrequestrepo

import com.issuesolver.data.network.auth.LoginService
import com.issuesolver.data.network.newrequest.NewRequestService
import com.issuesolver.domain.entity.networkModel.NewRequest
import com.issuesolver.domain.entity.networkModel.NewRequestResponseBody
import com.issuesolver.domain.entity.networkModel.RegisterResponseModel
import com.issuesolver.domain.entity.networkModel.RequestOtp
import retrofit2.Response
import javax.inject.Inject


interface NewRequestRepositoryInterface {
    suspend fun newRequest(categoryName: String, newRequest: NewRequest): Response<NewRequestResponseBody>
}


class NewRequestRepositoryImpl @Inject constructor(private val newRequestService: NewRequestService) :
    NewRequestRepositoryInterface {
    override suspend fun newRequest(categoryName: String, newRequest: NewRequest): Response<NewRequestResponseBody> {
        return newRequestService.newRequest(categoryName, newRequest)
    }


}