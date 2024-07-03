package com.issuesolver.domain.usecase.login

class ValidateFullNameUseCase {
    fun execute(fullName: String): ValidationResult {
        if(fullName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Ad ve Soyadinizi daxil edin"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}