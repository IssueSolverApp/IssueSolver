package com.issuesolver.presentation.login.password_change_page

data class PasswordChangePageState(
    val newpassword: String = "",
    val newpasswordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,

    val isPasswordShown: Boolean=false,
    val isRepeatedPasswordShown: Boolean=false,

    val isInputValid:Boolean=false,
    val errorMessageInput:String?=null,
    val isLoading:Boolean=false,
    val isSuccessfullyLoggedIn:Boolean=false,
    val errorMessageLoginProcess: String?=null
)
