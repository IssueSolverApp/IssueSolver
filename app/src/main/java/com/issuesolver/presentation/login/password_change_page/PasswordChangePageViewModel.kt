package com.issuesolver.presentation.login.password_change_page

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.entity.networkModel.ResetPasswordModel
import com.issuesolver.domain.useCase.OtpTrustUseCase
import com.issuesolver.domain.useCase.ResetPasswordUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import com.issuesolver.domain.useCase.login.ValidateRepeatedPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordChangePageViewModel @Inject constructor(

    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val otpTrustUseCase: OtpTrustUseCase

) : ViewModel() {

    private val token = otpTrustUseCase.getToken()


    private val _uiState = MutableStateFlow(PasswordChangePageState())
    val uiState: StateFlow<PasswordChangePageState> = _uiState.asStateFlow()

    private val _resetPassword: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val resetPassword: StateFlow<State> = _resetPassword



    fun resetPassword(request: ResetPasswordModel){
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
