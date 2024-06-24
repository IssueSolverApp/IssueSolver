package com.issuesolver.presentation.login.email_verification_page

import androidx.lifecycle.ViewModel
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EmailVerificationPageViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerificationPageState())
    val uiState: StateFlow<EmailVerificationPageState> = _uiState.asStateFlow()

    fun handleEvent(event: VerificationCodePageEvent) {
        when (event) {
            is VerificationCodePageEvent.EmailChanged -> {
                val result = validateEmailUseCase.execute(event.email)
                _uiState.value = uiState.value.copy(
                    email = event.email,
                    emailError = result.errorMessage,
                    isInputValid = result.successful
                )
            }
            is VerificationCodePageEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }
        }
    }
}



