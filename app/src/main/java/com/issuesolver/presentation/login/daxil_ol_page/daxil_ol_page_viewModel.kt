package com.issuesolver.presentation.login.daxil_ol_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaxilOlPageViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
): ViewModel() {

    var daxilOlPageState by mutableStateOf(DaxilOlPageState())
        private set

    fun onEmailInputChange(newValue: String) {
        daxilOlPageState = daxilOlPageState.copy(email = newValue)
        validateEmail(newValue)
    }

    fun onPasswordInputChange(newValue: String) {
        daxilOlPageState = daxilOlPageState.copy(password = newValue)
        validatePassword(newValue)
    }

    fun onToggleVisualTransformation() {
        daxilOlPageState = daxilOlPageState.copy(isPasswordShown = !daxilOlPageState.isPasswordShown)
    }

    fun onLoginClick() {
        daxilOlPageState = daxilOlPageState.copy(isLoading = true)
        viewModelScope.launch {
            val emailResult = validateEmailUseCase.execute(daxilOlPageState.email)
            val passwordResult = validatePasswordUseCase.execute(daxilOlPageState.password)

            if (emailResult.successful && passwordResult.successful) {
                // Simulate login success
                daxilOlPageState = daxilOlPageState.copy(
                    isSuccessfullyLoggedIn = true,
                    isLoading = false
                )
                // Add additional login logic here
            } else {
                daxilOlPageState = daxilOlPageState.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    isLoading = false
                )
            }
        }
    }

    private fun validateEmail(email: String) {
        val result = validateEmailUseCase.execute(email)
        daxilOlPageState = daxilOlPageState.copy(emailError = result.errorMessage)
    }

    private fun validatePassword(password: String) {
        val result = validatePasswordUseCase.execute(password)
        daxilOlPageState = daxilOlPageState.copy(passwordError = result.errorMessage)
    }
}
