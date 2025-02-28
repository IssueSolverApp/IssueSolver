package com.issuesolver.domain.usecase.login.local

import com.issuesolver.domain.usecase.ValidationResult

class ValidateNewPasswordUseCase {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Şifrə ən azı 8 simvoldan ibarət olmalıdır"
            )
        }
        if (!password.any { it.isUpperCase() } ||
            !password.any { it.isLowerCase() } ||
            !password.any { it.isDigit() }) {
            return ValidationResult(
                successful = false,
                errorMessage = "Şifrədə ən azı bir böyük latın hərfi, bir kiçik latın hərfi və rəqəm istifadə olunmalıdır"
            )
        }
        if ((password != repeatedPassword) && (repeatedPassword.isNotBlank())) {
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