package com.issuesolver.presentation.login.password_change_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.login.ResetPasswordModel
import com.issuesolver.domain.usecase.login.backend.OtpTrustUseCase
import com.issuesolver.domain.usecase.login.backend.ResetPasswordUseCase
import com.issuesolver.domain.usecase.login.local.ValidateNewPasswordUseCase
import com.issuesolver.domain.usecase.login.local.ValidateRepeatedPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordChangePageViewModel @Inject constructor(

    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validateNewPasswordUseCase: ValidateNewPasswordUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val otpTrustUseCase: OtpTrustUseCase

) : ViewModel() {

    private val token = otpTrustUseCase.getToken()
    private val _uiState = MutableStateFlow(PasswordChangePageState())
    val uiState: StateFlow<PasswordChangePageState> = _uiState.asStateFlow()

    private val _resetPassword: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val resetPassword: StateFlow<State> = _resetPassword

    fun resetPassword(request: ResetPasswordModel) {
        viewModelScope.launch {
            resetPasswordUseCase(token!!, request).collect{
                //_resetPassword.value = it
                when(it){
                    is Resource.Success -> {
                        _resetPassword.emit(State.error(it.message))
                    }
                    is Resource.Error -> {
                        _resetPassword.emit(State.success())
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    fun handleEvent(event: PasswordChangePageEvent) {
        when (event) {
            is PasswordChangePageEvent.PasswordChanged -> {
                val result = validateNewPasswordUseCase.execute(
                    event.newpassword,
                    _uiState.value.repeatedPassword
                )
                val result2 = validateRepeatedPasswordUseCase.execute(
                    event.newpassword,
                    _uiState.value.repeatedPassword
                )
                _uiState.value = _uiState.value.copy(
                    newpassword = event.newpassword,
                    newpasswordError = if (result.successful) null else _uiState.value.newpasswordError,
                    isInputValid = result.successful && result2.successful &&
                            validateRepeatedPasswordUseCase.execute(
                        event.newpassword,
                        _uiState.value.repeatedPassword
                    ).successful
                )
            }

            is PasswordChangePageEvent.RepeatedPasswordChanged -> {
                val result = validateRepeatedPasswordUseCase.execute(
                    _uiState.value.newpassword,
                    event.repeatedPassword
                )
                val result2 = validateNewPasswordUseCase.execute(
                    _uiState.value.newpassword,
                    event.repeatedPassword
                )
                _uiState.value = _uiState.value.copy(
                    repeatedPassword = event.repeatedPassword,
                    repeatedPasswordError = if (result.successful) null else _uiState.value.repeatedPasswordError,
                    isInputValid = validateNewPasswordUseCase.execute(
                        _uiState.value.newpassword,
                        event.repeatedPassword
                    ).successful && result.successful && result2.successful
                )
            }

            is PasswordChangePageEvent.Submit -> {
                if (uiState.value.isInputValid) {
                }
            }
        }
    }
}
