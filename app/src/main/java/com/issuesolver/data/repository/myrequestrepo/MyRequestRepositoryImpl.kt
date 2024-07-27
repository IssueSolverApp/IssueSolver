package com.issuesolver.data.repository.myrequestrepo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.issuesolver.data.network.home.RequestService
import com.issuesolver.data.network.myrequest.MyRequestService
import com.issuesolver.data.pagination.MyRequestPagingSource
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject

//interface MyRequestRepositoryInterface{
//    suspend fun myRequest(page:Int, size:Int): Response<FilterResponseModel>
//}
//
//class MyRequestRepositoryImpl @Inject constructor(private val myRequestService: MyRequestService):MyRequestRepositoryInterface {
//    override suspend fun myRequest(page: Int, size: Int): Response<FilterResponseModel> {
//        return myRequestService.myRequest(page, size)
//    }
//}

interface MyRequestRepositoryInterface {
    fun getMyRequests(): Pager<Int, FilterData>
}

class MyRequestRepositoryImpl @Inject constructor(
    private val myRequestService: MyRequestService
) : MyRequestRepositoryInterface {
    override fun getMyRequests(): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = { MyRequestPagingSource(myRequestService) }
        )
    }
}