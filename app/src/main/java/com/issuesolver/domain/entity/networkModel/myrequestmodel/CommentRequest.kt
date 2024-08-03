package com.issuesolver.domain.entity.networkModel.myrequestmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CommentRequest(
    val commentText:String? = null
): Parcelable


