package com.issuesolver.presentation.login.verification_code_page

data class VerificationCodePageState(
    var otpValue: String = "",
    var isOtpFilled: Boolean = false,
    var remainingTime: Int = 30,
    var errorMessage: String? = null,
    var isLoading: Boolean = false,
    var isSuccess: Boolean = false
)
