package com.issuesolver.domain.useCase.login

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)

