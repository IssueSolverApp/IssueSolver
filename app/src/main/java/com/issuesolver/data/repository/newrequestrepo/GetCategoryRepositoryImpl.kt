package com.issuesolver.data.repository.newrequestrepo

import com.issuesolver.data.network.newrequest.NewRequestService
import com.issuesolver.domain.entity.networkModel.category.GetCategoryResponseModel
import retrofit2.Response
import javax.inject.Inject


interface GetCategoryRepositoryInterface {
    suspend fun getCategory(): Response<GetCategoryResponseModel>

}

class GetCategoryRepositoryImpl @Inject constructor(private val newRequestService: NewRequestService):GetCategoryRepositoryInterface {
    override suspend fun getCategory(): Response<GetCategoryResponseModel> {
        return newRequestService.getCategory()
    }
}