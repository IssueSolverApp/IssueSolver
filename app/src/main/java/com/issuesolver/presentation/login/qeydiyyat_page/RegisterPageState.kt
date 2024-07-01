package com.issuesolver.presentation.login.qeydiyyat_page

data class RegisterPageState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val fullName: String = "",
    val fullNameError: String? = null,
    val isInputValid: Boolean = false
)