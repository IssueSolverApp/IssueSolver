package com.issuesolver.data.repository.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.issuesolver.data.network.home.RequestService
import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.data.pagination.HomePagingSource
import com.issuesolver.data.pagination.MyRequestPagingSource
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject

//interface RequestInterface{
//    suspend fun request(page:Int, size:Int): Response<FilterResponseModel>
//}
//class RequestRepositoryImpl @Inject constructor(private val requestService: RequestService):RequestInterface{
//    override suspend fun request(page: Int, size: Int): Response<FilterResponseModel> {
//        return requestService.filter( page,size)
//    }
//
//}

interface RequestInterface {
    fun request(): Pager<Int, FilterData>
}

class RequestRepositoryImpl @Inject constructor(
    private val requestService: RequestService
) : RequestInterface {
    override fun request(): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 100),
            pagingSourceFactory = { HomePagingSource(requestService) }
        )
    }
}