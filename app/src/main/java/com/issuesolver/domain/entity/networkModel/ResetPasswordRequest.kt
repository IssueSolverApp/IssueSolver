package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

class ResetPasswordRequest (
    val password: String? = null,
    val confirmPassword: String? = null
) : Parcelable

