package com.issuesolver.presentation.profile.profile

data class ProfileScreenState (
    val fullName: String? = null,
    val email: String? = null,
    val fullNameError: String? = null,
    val emailError: String? = null,
    val isLoading: Boolean = false
)