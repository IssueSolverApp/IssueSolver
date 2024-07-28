package com.issuesolver.domain.usecase.home.backend

import androidx.paging.PagingData
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.home.TestFilterRepository
import com.issuesolver.domain.entity.networkModel.home.FilterData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TestUseCase @Inject constructor(private val repository: TestFilterRepository) {
    operator fun invoke(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String
    ): Flow<PagingData<FilterData>> {
        return repository.getFilterResults(status, categoryName, organizationName, days).flow
    }
}
