package com.issuesolver.presentation.login.password_change_page

data class password_change_state(
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
)
