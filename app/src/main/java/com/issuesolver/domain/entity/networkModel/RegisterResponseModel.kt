package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponseModel (
    val timespam: String? = null,
    val url: String? = null,
    val title: String? = null,
    val success: Boolean? = null,
    val status: Int? = null,
    val message: String? = null
):Parcelable


//{
//    "success": true,
//    "status": 201,
//    "message": "yu qu successfully regsitered,  please verify account "
//}

//{
//    "TimeSpam": "2024-06-23T14:47:07.1501188Z",
//    "Url": "https://govermentauthapi20240610022027.azurewebsites.net/api/Auths/register",
//    "Title": "Validation Exception",
//    "Success": false,
//    "Status": 400,
//    "Message": "Email Address duzgun deyil  "
//}