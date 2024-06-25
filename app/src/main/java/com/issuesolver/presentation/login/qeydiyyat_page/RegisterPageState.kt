package com.issuesolver.presentation.login.qeydiyyat_page

data class RegisterPageState (
    var email: String = "",
    var emailError: String? = null,
    var password: String = "",
    var passwordError: String? = null,
    var repeatedPassword: String = "",
    var repeatedPasswordError: String? = null,
    var isInputValid:Boolean=false,
    var errorMessageInput:String?=null
    )