package com.issuesolver.domain.usecase.home.backend

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.pagination.FilterPagingSource
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.home.RequestInterface
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFilteredResultsUseCase@Inject constructor(private val filterRepository: FilterRepository) {

    fun execute(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String
    ): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                FilterPagingSource(filterRepository, status, categoryName, organizationName, days)
            }
        )
    }
}
