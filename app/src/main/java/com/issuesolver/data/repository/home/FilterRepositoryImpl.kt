package com.issuesolver.data.repository.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.issuesolver.data.network.home.FilterService
import com.issuesolver.data.network.home.RequestService
import com.issuesolver.data.pagination.FilterPagingSource
import com.issuesolver.data.pagination.HomePagingSource
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import retrofit2.Response
import javax.inject.Inject


//interface FilterInterface{
//    suspend fun filter(status:String, categoryName:String,organizationName:String,days:String): Response<FilterResponseModel>
//}
//class FilterRepositoryImpl @Inject constructor(private val filterService: FilterService):
//    FilterInterface {
//
//    override suspend fun filter(
//        status: String,
//        categoryName: String,
//        organizationName: String,
//        days: String
//    ): Response<FilterResponseModel> {
//        return filterService.filter( status,
//            categoryName,
//            organizationName,
//            days)
//    }
//}

//interface FilterInterface{
//    suspend fun filter(status:String, categoryName:String,organizationName:String,days:String): Response<FilterResponseModel>
//}
//class FilterRepositoryImpl @Inject constructor(private val filterService: FilterService):
//    FilterInterface {
//
//    override suspend fun filter(
//        status: String,
//        categoryName: String,
//        organizationName: String,
//        days: String
//    ): Response<FilterResponseModel> {
//        return filterService.filter( status,
//            categoryName,
//            organizationName,
//            days)
//    }
//}

interface FilterInterface {
    fun filter(status: String, categoryName: String, organizationName: String, days: String): Pager<Int, FilterData>
}


class FilterRepositoryImpl @Inject constructor(
    private val filterService: FilterService
) : FilterInterface {
    override fun filter(status: String, categoryName: String, organizationName: String, days: String): Pager<Int, FilterData> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { FilterPagingSource(filterService, status, categoryName, organizationName, days) }
        )
    }
}
