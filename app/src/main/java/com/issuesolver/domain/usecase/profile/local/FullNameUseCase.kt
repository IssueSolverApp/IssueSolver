package com.issuesolver.domain.usecase.profile.local

import com.issuesolver.domain.usecase.ValidationResult

class FullNameUseCase {
    fun execute(fullName: String): ValidationResult {
        if(fullName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Ad ve Soyadınızı daxil edin"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}