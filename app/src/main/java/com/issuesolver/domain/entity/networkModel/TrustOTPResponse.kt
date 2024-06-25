package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrustOTPResponse (

    val data: String? = null,
    val success: Boolean? = null,
    val status: Int? = null,
    val message: String? = null
): Parcelable