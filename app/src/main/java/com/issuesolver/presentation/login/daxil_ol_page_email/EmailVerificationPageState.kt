package com.issuesolver.presentation.login.daxil_ol_page_email

data class EmailVerificationPageState(
    var email: String = "",
var emailError: String? = null,
    val isInputValid:Boolean=false,
    val errorMessageInput:String?=null,
    val isLoading:Boolean=false,
    val isSuccessfullyLoggedIn:Boolean=false,
    val errorMessageLoginProcess: String?=null
)

