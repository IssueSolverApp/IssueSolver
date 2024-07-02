package com.issuesolver.domain.usecase.login

class ValidateRepeatedPasswordUseCase {
    fun execute(newpassword: String, repeatedPassword: String): ValidationResult {

        if (newpassword != repeatedPassword) {
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

