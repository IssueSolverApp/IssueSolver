package com.issuesolver.presentation.profile.new_password

data class NewPasswordScreenState (
    val currentPassword: String = "",
    val currentPasswordError: String? = null,
    val newPassword: String = "",
    val newPasswordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,

    )
