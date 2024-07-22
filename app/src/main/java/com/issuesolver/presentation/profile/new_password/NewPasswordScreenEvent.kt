package com.issuesolver.presentation.profile.new_password


sealed class NewPasswordScreenEvent {
    data class CurrentPasswordChanged(val currentPassword: String) : NewPasswordScreenEvent()
    data class NewPasswordChanged(val newPassword: String) : NewPasswordScreenEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : NewPasswordScreenEvent()

    object Submit : NewPasswordScreenEvent()
}