package com.issuesolver.presentation.login.password_change_page



sealed class PasswordChangePageEvent {
    data class PasswordChanged(val newpassword: String) : PasswordChangePageEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : PasswordChangePageEvent()

    object Submit : PasswordChangePageEvent()
}
