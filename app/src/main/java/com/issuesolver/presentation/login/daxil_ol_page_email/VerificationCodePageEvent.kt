package com.issuesolver.presentation.login.daxil_ol_page_email


sealed class VerificationCodePageEvent {
    data class EmailChanged(val email: String) : VerificationCodePageEvent()
    object Submit : VerificationCodePageEvent()
}