package com.issuesolver.domain.entity.networkModel.myrequestmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentData(
    val authority: String? = null,
    val commentId: Int? = null,
    val commentText: String? = null,
    val createDate: String? = null,
    val fullName: String? = null
) : Parcelable

