package com.issuesolver.presentation.login.email_verification_page


sealed class VerificationCodePageEvent {
    data class EmailChanged(val email: String) : VerificationCodePageEvent()

    object Submit : VerificationCodePageEvent()
}