package com.issuesolver.domain.usecase.login

import android.util.Patterns

class ValidateEmailUseCase() {
     fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Bu e-poçt ünvanı boş olmamalıdır"
            )
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "E-poçt yanlışdır"
            )
        }


        return ValidationResult(
            successful = true
        )
    }
}