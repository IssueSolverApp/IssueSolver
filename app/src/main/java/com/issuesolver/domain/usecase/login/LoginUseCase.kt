package com.issuesolver.domain.usecase.login


class LoginUseCase() {
    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false)
        }

        return ValidationResult(
            successful = true
        )
    }
}