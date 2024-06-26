package com.issuesolver.presentation.login.password_change_page

import androidx.lifecycle.ViewModel
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import com.issuesolver.domain.useCase.login.ValidateRepeatedPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PasswordChangePageViewModel @Inject constructor(

    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase

) : ViewModel() {

    private val _uiState = MutableStateFlow(PasswordChangePageState())
    val uiState: StateFlow<PasswordChangePageState> = _uiState.asStateFlow()

    fun handleEvent(event: PasswordChangePageEvent) {
        when (event) {

            is PasswordChangePageEvent.PasswordChanged -> {
                val result = validatePasswordUseCase.execute(event.newpassword)
                _uiState.value = uiState.value.copy(
                    newpassword = event.newpassword,
                    newpasswordError = result.errorMessage,
                    isInputValid = result.successful && validateRepeatedPasswordUseCase.execute(uiState.value.repeatedPassword, uiState.value.newpassword).successful
                )
            }

            is PasswordChangePageEvent.RepeatedPasswordChanged -> {
                val result = validateRepeatedPasswordUseCase.execute(_uiState.value.newpassword, event.repeatedPassword)
                _uiState.value = _uiState.value.copy(
                    repeatedPassword = event.repeatedPassword,
                    repeatedPasswordError = result.errorMessage,
                    isInputValid = validatePasswordUseCase.execute(_uiState.value.newpassword).successful && result.successful
                )
            }

            is PasswordChangePageEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }
        }
    }
}
