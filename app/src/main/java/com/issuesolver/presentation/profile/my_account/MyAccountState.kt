package com.issuesolver.presentation.profile.my_account

data class MyAccountState (
    val fullName: String? = null,
    val fullNameError: String? = null,
    val email: String? = null,
    val isLoading: Boolean = false
)