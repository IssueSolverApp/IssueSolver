package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponseModel (
    val TimeSpam: String? = null,
    val Url: String? = null,
    val Title: String? = null,
    val Success: Boolean? = null,
    val Status: Int? = null,
    val Message: String? = null
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