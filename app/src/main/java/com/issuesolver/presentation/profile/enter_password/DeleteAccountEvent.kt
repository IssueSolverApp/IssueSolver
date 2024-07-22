package com.issuesolver.presentation.profile.enter_password


sealed class DeleteAccountEvent {
    data class PasswordChanged(val password: String) : DeleteAccountEvent()

    object Submit : DeleteAccountEvent()


}