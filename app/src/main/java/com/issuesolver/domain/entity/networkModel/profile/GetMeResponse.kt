package com.issuesolver.domain.entity.networkModel.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class GetMeResponse(
    var data: ProfileData = ProfileData(),
    var success: Boolean? = null,
    var message: String? = null
) : Parcelable

@Parcelize
data class ProfileData(
    val email: String? = null,
    val fullName: String? = null,
    val createdTime: String? = null,
    val modifiedTime: String? = null
) : Parcelable