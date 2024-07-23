package com.issuesolver.domain.entity.networkModel.home


data class FilterResponseModel(
    val data: List<CategoryData2>,
    val message: String,
    val success: Boolean
)
data class CategoryData2(
    val categoryId: Int,
    val categoryName: String
)