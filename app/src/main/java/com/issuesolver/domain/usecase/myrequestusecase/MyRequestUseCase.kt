package com.issuesolver.domain.usecase.myrequestusecase

import androidx.paging.PagingData
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.repository.home.RequestInterface
import com.issuesolver.data.repository.myrequestrepo.MyRequestRepositoryInterface
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MyRequestUseCase @Inject constructor(
    private val repository: MyRequestRepositoryInterface
) {
    operator fun invoke(): Flow<PagingData<FilterData>> {
        return repository.getMyRequests().flow
    }
}
