package com.issuesolver.domain.entity.networkModel.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UpdatePasswordRequest(
    val currentPassword: String? = null,
    val password: String? = null,
    val confirmPassword: String? = null,

    ) : Parcelable