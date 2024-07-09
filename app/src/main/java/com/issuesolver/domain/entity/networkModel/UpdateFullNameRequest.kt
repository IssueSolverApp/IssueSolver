package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UpdateFullNameRequest(
    val fullName: String? = null,
) : Parcelable