package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequestModel(
    val email: String? = null,
    val fullName: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null
) :Parcelable


