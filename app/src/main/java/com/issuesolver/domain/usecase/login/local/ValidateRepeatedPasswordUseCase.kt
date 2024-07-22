package com.issuesolver.domain.usecase.login.local

import com.issuesolver.domain.usecase.ValidationResult

class ValidateRepeatedPasswordUseCase {
    fun execute(password: String, repeatedPassword: String): ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Hər iki şifrə dəqiq eyni olmalıdır"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}

