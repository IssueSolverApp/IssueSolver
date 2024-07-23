package com.issuesolver.domain.entity.networkModel.home

import java.util.Locale.Category


data class FilterResponseModel(
    val data: List<FilterData>,
    val message: String,
    val success: Boolean
)
data class FilterData(
    val requestId: Int,
    val fullName: String,
    val address:String,
    val description:String,
    val status:String,
    val organizationName:String,
    val createDate:String,
    val commentCount:Int,
    val likeCount:Int,
    val likeSuccess:Boolean,
    val category:List<CategoryData>
)
data class CategoryData(
    val categoryId:Int,
    val categoryName:String
)

