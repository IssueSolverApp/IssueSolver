package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewRequestResponseBody(
    val data: String? = null,
    val message: String? = null,
    val success: Boolean? = null
) : Parcelable

//{
//    "data": "string",
//    "message": "string",
//    "success": true
//}
