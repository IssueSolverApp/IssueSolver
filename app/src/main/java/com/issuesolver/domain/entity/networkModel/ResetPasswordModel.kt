package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResetPasswordModel(
    val password: String? = null,
    val confirmPassword: String?=null

): Parcelable

//"password": "string",
//"confirmPassword": "string"