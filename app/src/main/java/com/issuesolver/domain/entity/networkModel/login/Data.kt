package com.issuesolver.domain.entity.networkModel.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    var accessToken: String? = null,
    var refreshToken: String? = null
):Parcelable
