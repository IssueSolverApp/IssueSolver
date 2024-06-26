package com.issuesolver.domain.useCase.login

import android.util.Patterns

class ValidateFullNameUseCase {
    fun execute(fullName: String): ValidationResult {
        if(fullName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Ad ve Soyadinizi daxil edin"
            )
        }


        return ValidationResult(
            successful = false
        )
    }
}