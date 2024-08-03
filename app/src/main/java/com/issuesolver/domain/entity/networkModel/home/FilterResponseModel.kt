package com.issuesolver.domain.entity.networkModel.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FilterResponseModel(
    val data: List<FilterData>?= listOf(),
    val message: String? = null,
    val fullName: String? = null,
    val success: Boolean? = null
):Parcelable

@Parcelize
data class FilterData(
    val requestId: Int? = null,
    val fullName: String? = null,
    val address:String? = null,
    val description:String? = null,
    val status:String? = null,
    val organizationName:String? = null,
    val createDate:String? = null,
    val commentCount:Int? = null,
    val likeCount:Int? = null,
    val likeSuccess:Boolean? = null,
    val category:CategoryData = CategoryData()
):Parcelable

@Parcelize
data class CategoryData(
    val categoryId:Int? = null,
    val categoryName:String? = null
):Parcelable

