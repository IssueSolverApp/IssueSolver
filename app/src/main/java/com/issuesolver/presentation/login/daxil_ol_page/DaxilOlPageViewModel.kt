package com.issuesolver.presentation.login.daxil_ol_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.issuesolver.domain.usecase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DaxilOlPageViewModel @Inject constructor(

    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DaxilOlPageState())
    val uiState: StateFlow<DaxilOlPageState> = _uiState.asStateFlow()

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
                    // Proceed with backend authentication
                    // Handle backend response and update UI state accordingly
                }
            }
        }
    }
}