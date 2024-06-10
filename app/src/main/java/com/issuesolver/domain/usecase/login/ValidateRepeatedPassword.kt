package com.issuesolver.domain.usecase.login

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
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