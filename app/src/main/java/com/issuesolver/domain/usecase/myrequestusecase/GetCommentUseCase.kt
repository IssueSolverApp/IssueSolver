package com.issuesolver.domain.usecase.myrequestusecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.issuesolver.common.Resource
import com.issuesolver.data.pagination.CommentsPagingSource
import com.issuesolver.data.pagination.FilterPagingSource
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.myrequestrepo.CommentRepository
import com.issuesolver.data.repository.myrequestrepo.GetRequestByIdRepositoryInterface
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.w3c.dom.Comment
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCommentsUseCase @Inject constructor(
    private val repository: CommentRepository
) {
    operator fun invoke(requestId: Int?): Flow<PagingData<CommentData>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { CommentsPagingSource(repository, requestId) }
        ).flow
    }
}
