package com.issuesolver.presentation.login.daxil_ol_page

import androidx.lifecycle.ViewModel
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(

    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginPageState())
    val uiState: StateFlow<LoginPageState> = _uiState.asStateFlow()

    fun handleEvent(event: LoginPageEvent) {
        when (event) {
            is LoginPageEvent.EmailChanged -> {
                val result = validateEmailUseCase.execute(event.email)
                _uiState.value = uiState.value.copy(
                    email = event.email,
                    emailError = result.errorMessage,
                    isInputValid = result.successful && validatePasswordUseCase.execute(uiState.value.password).successful
                )
            }
            is LoginPageEvent.PasswordChanged -> {
                val result = validatePasswordUseCase.execute(event.password)
                _uiState.value = uiState.value.copy(
                    password = event.password,
                    passwordError = result.errorMessage,
                    isInputValid = result.successful && validateEmailUseCase.execute(uiState.value.email).successful
                )
            }
            is LoginPageEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }
        }
    }
}
