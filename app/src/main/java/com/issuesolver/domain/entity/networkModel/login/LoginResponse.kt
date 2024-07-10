package com.issuesolver.domain.entity.networkModel.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    var data: Data? = Data(),
    var success: Boolean? = null,
    var status: Int? = null,
    var message: String? = null
) : Parcelable
