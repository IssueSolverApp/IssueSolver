package com.issuesolver.domain.entity.networkModel.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResendOtpModel(
    val email: String? = null
):Parcelable
