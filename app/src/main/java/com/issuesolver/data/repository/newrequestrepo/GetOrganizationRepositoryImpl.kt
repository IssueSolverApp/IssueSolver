package com.issuesolver.data.repository.newrequestrepo

import com.issuesolver.data.network.newrequest.NewRequestService
import com.issuesolver.domain.entity.networkModel.category.GetCategoryResponseModel
import com.issuesolver.domain.entity.networkModel.organization.GetOrganizationResponseModel
import retrofit2.Response
import javax.inject.Inject

interface GetOrganizationRepositoryInterface {
    suspend fun getOrganization(): Response<GetOrganizationResponseModel>

}

class GetOrganizationRepositoryImpl @Inject constructor(private val newRequestService: NewRequestService):GetOrganizationRepositoryInterface {
    override suspend fun getOrganization(): Response<GetOrganizationResponseModel> {
        return newRequestService.getOrganization()
    }

}