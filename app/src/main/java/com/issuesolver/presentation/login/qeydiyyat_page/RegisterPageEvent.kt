package com.issuesolver.presentation.login.qeydiyyat_page



sealed class RegisterPageEvent {

    data class EmailChanged(val email: String) : RegisterPageEvent()

    data class PasswordChanged(val password: String): RegisterPageEvent()

    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegisterPageEvent()

    object Submit : RegisterPageEvent()

}