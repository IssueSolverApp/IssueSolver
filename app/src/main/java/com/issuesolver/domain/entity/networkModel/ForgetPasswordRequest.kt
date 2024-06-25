package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize

class ForgetPasswordRequest (
        val email: String? = null
    ) : Parcelable
