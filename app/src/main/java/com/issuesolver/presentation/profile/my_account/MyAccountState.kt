package com.issuesolver.presentation.profile.my_account

data class MyAccountState (
    val fullName: String = "",
    val fullNameError: String? = null,
    val email: String? = null,
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    )