package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterErrorResponseModel (
    val timestamp: String? = null,
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val errorDetail: String? = null,
    val path: String? = null
):Parcelable