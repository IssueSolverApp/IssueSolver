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

//{
//    "data": [
//    {
//        "commentId": 0,
//        "fullName": "string",
//        "authority": "string",
//        "commentText": "string",
//        "createDate": "2024-07-25T10:54:09.469Z"
//    }
//    ],
//    "message": "string",
//    "success": true,
//    "fullName": "string"
//}