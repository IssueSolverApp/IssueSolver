package com.issuesolver.domain.entity.networkModel.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UpdateFullNameRequest(
    val fullName: String? = null,
) : Parcelable