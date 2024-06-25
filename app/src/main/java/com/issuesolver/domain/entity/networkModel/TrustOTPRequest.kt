package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrustOTPRequest(
    val otpCode: String? = null,
): Parcelable