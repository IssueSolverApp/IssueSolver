package com.issuesolver.domain.usecase.home.backend

import androidx.paging.PagingData
import com.issuesolver.data.repository.home.RequestInterface
import com.issuesolver.domain.entity.networkModel.home.FilterData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class RequestUseCase @Inject constructor(
    private val request: RequestInterface
) {
    operator fun invoke(): Flow<PagingData<FilterData>> {
        return request.request().flow
    }
}