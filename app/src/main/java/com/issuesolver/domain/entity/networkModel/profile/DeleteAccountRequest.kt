package com.issuesolver.domain.entity.networkModel.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class DeleteAccountRequest(
    val password: String? = null,
) : Parcelable