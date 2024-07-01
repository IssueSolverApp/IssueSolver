package com.issuesolver.domain.usecase.login

import android.util.Patterns
import com.issuesolver.domain.useCase.login.ValidationResult

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