package com.issuesolver.domain.usecase.login

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if(password.length < 7) {
            return ValidationResult(
                successful = false,
                errorMessage = "Şifrə ən azı 6 simvoldan ibarət olmalıdır."
            )
        }


        val apiError = null
        if((apiError != null && apiError.status == 400 && apiError.message == "user not found") {
            return ValidationResult(
                successful = false,
                errorMessage = "E-poçt və ya şifrə yanlışdır."
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}