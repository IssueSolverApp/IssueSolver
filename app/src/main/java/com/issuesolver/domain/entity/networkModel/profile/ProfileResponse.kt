package com.issuesolver.domain.entity.networkModel.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileResponse(
    var success: Boolean? = null,
    var message: String? = null
) : Parcelable