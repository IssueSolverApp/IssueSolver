package com.issuesolver.presentation.login.daxil_ol_verification_code



sealed class OtpVerificationCodePageEvent {
    data class OtpChanged(val otp: String) : OtpVerificationCodePageEvent()
    object Submit : OtpVerificationCodePageEvent()

}