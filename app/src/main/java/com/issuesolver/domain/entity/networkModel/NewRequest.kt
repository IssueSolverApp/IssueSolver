package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewRequest(
    val address: String,
    val description: String,
    val organizationName: String
):Parcelable

