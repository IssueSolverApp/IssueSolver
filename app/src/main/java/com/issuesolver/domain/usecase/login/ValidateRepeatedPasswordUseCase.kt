package com.issuesolver.domain.useCase.login

class ValidateRepeatedPasswordUseCase {
    fun execute(newpassword: String, repeatedPassword: String): ValidationResult {



        if(newpassword != repeatedPassword) {
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

