package com.issuesolver.presentation.login.qeydiyyat_page

data class RegisterPageState (
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isInputValid:Boolean=false,
    val errorMessageInput:String?=null
    )