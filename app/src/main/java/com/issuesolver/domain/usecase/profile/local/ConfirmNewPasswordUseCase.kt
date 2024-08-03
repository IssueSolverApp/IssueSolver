package com.issuesolver.domain.usecase.profile.local

import com.issuesolver.domain.usecase.ValidationResult

class ConfirmNewPasswordUseCase {
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