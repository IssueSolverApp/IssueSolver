package com.issuesolver.presentation.profile.new_password

data class NewPasswordScreenState (
    val currentPassword: String? = null,
    val currentPasswordError: String? = null,
    val newPassword: String? = null,
    val newPasswordError: String? = null,
    val confirmPassword: String? = null,
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false
)
