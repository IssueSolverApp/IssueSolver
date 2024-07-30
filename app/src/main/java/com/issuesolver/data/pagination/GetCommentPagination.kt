package com.issuesolver.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.issuesolver.data.repository.home.FilterRepository
import com.issuesolver.data.repository.myrequestrepo.CommentRepository
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import org.w3c.dom.Comment
import javax.inject.Inject

//class GetCommentPagination {
//}


//class GetCommentPagination(
//    private val filterRepository: GetCommentRepositoryInterface,
//    private val requestId:Int?
//) : PagingSource<Int, CommentData>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentData> {
//        val page = params.key ?: 1
//        return try {
//            val response = filterRepository.getComment(page, params.loadSize,requestId)
//            val items = response.body()?.data ?: emptyList()
//            LoadResult.Page(
//                data = items,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if (items.isEmpty()) null else page + 1
//            )
//        } catch (exception: Exception) {
//            LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, CommentData>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}



class CommentsPagingSource@Inject constructor(
    private val repository: CommentRepository,
    private val requestId: Int?
) : PagingSource<Int, CommentData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommentData> {
        val page = params.key ?: 0
        return try {
            val response = repository.getComments(requestId, page, params.loadSize)
            if (response.isSuccessful) {
                val comments = response.body()?.data ?: emptyList()
                LoadResult.Page(
                    data = comments,
                    prevKey = null,//if (page == 0) null else page - 1,
                    nextKey = if (comments.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("Ошибка загрузки комментариев"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CommentData>): Int? {
        // Используется для определения ключа для обновления
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
