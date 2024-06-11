package com.issuesolver.domain.entity.mapper

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    val access_token: String? = null,
    val refresh_token: String? = null
) : Parcelable