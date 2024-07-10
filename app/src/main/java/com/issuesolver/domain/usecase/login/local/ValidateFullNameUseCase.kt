package com.issuesolver.domain.usecase.login.local

import com.issuesolver.domain.usecase.ValidationResult

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