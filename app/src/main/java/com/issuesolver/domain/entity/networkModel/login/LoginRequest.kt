package com.issuesolver.domain.entity.networkModel.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    val email: String? = null,
    val password: String? = null,
) : Parcelable