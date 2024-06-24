package com.issuesolver.presentation.login.login_page

sealed class LoginPageEvent {
    data class EmailChanged(val email: String) : LoginPageEvent()
    data class PasswordChanged(val password: String) : LoginPageEvent()
    object Submit : LoginPageEvent()
}
