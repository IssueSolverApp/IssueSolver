package com.issuesolver.domain.entity.networkModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtpTrustResponse(
    val data: String? = null,
    val success: Boolean? = null,
    val status: Int? = null,
    val message: String? = null
):Parcelable

//"data": "uDClbXoG8ycBzU+EWkOxLvhLxwPFBPKlfUGnqOy7wjZwdjfU85IXs4ivAb+EgQo8+9a4TP97beFCYCev8Lpgeg==",
//"success": true,
//"status": 200,
//"message": "otp is confirm.."