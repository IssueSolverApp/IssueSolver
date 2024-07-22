package com.issuesolver.domain.usecase.login.local

import android.util.Patterns
import com.issuesolver.domain.usecase.ValidationResult

class ValidateEmailUseCase() {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Bu e-poçt ünvanı boş olmamalıdır"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "E-poçt formatı yanlışdır"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}