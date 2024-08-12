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


interface RequestInterface {
    fun request(): Pager<Int, FilterData>
}

class RequestRepositoryImpl @Inject constructor(
    private val requestService: RequestService
) : RequestInterface {
    override fun request(): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = { HomePagingSource(requestService) }
        )
    }
}