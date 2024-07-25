package com.issuesolver.domain.entity.networkModel.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LikeResponse(
    val data: String? = null,
    val fullName: String? = null,
    val message: String? = null,
    val success: Boolean? = null
):Parcelable