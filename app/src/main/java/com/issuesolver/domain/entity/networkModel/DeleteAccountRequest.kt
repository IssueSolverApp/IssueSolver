package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class DeleteAccountRequest(
    val aa: String? = null,
    val bb: String? = null,
) : Parcelable