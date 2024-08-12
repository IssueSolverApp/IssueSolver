package com.issuesolver.domain.entity.networkModel.login

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

