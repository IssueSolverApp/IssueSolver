package com.issuesolver.domain.usecase.login

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)

