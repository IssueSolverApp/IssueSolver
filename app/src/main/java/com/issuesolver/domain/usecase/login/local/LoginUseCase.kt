package com.issuesolver.domain.usecase.login.local

import com.issuesolver.domain.usecase.ValidationResult


class LoginUseCase() {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}