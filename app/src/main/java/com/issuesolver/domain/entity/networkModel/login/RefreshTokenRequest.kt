package com.issuesolver.domain.entity.networkModel.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefreshTokenRequest(
    val token: String
): Parcelable
