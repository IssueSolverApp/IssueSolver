package com.issuesolver.domain.entity.networkModel.myrequestmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentResponse(
    val data: List<CommentData>? = listOf(),
    val fullName: String? = null,
    val message: String? = null,
    val success: Boolean? = null
) : Parcelable
