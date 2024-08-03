package com.issuesolver.domain.entity.networkModel.category


data class GetCategoryResponseModel(
    val data: List<CategoryData>,
    val message: String,
    val success: Boolean
)



//{
//    "data": [
//    {
//        "categoryId": 0,
//        "categoryName": "string"
//    }
//    ],
//    "message": "string",
//    "success": true
//}