package com.issuesolver.presentation.login.qeydiyyat_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.login.RegisterRequestModel
import com.issuesolver.domain.usecase.login.backend.RegisterUseCase
import com.issuesolver.domain.usecase.login.local.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.local.ValidateFullNameUseCase
import com.issuesolver.domain.usecase.login.local.ValidateNewPasswordUseCase
import com.issuesolver.domain.usecase.login.local.ValidateRepeatedPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateNewPasswordUseCase: ValidateNewPasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validateFullNameUseCase: ValidateFullNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterPageState())
    val uiState: StateFlow<RegisterPageState> = _uiState.asStateFlow()

    private var _registerState: MutableStateFlow<State?> = MutableStateFlow(null)
    val registerState: StateFlow<State?> = _registerState.asStateFlow()

    fun register(request: RegisterRequestModel) {
        viewModelScope.launch {
            registerUseCase(request).collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        _registerState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _registerState.emit(State.error(resource.message))
                        _uiState.value = uiState.value.copy(emailError = resource.message)
                    }

                    is Resource.Success -> {
                        _registerState.emit(State.success())

                    }


                }
            }
        }
    }

    fun clearRegisterState() {
        _registerState.value = null
    }

    fun handleEvent(event: RegisterPageEvent) {
        when (event) {
            is RegisterPageEvent.FullNameChanged -> {

                val result = validateFullNameUseCase.execute(event.fullName)
                _uiState.value = uiState.value.copy(
                    fullName = event.fullName,
                    fullNameError = result.errorMessage,
                    isInputValid = result.successful &&
                            validateNewPasswordUseCase.execute(
                                uiState.value.repeatedPassword,
                                uiState.value.password
                            ).successful &&
                            validateRepeatedPasswordUseCase.execute(
                                uiState.value.repeatedPassword,
                                uiState.value.password
                            ).successful &&
                            validateEmailUseCase.execute(uiState.value.email).successful
                )
            }

            is RegisterPageEvent.EmailChanged -> {
                val result = validateEmailUseCase.execute(event.email)
                _uiState.value = uiState.value.copy(
                    email = event.email,
                    emailError = result.errorMessage,
                    isInputValid = result.successful &&
                            validateNewPasswordUseCase.execute(
                                uiState.value.repeatedPassword,
                                uiState.value.password
                            ).successful &&
                            validateFullNameUseCase.execute(uiState.value.fullName).successful &&
                            validateRepeatedPasswordUseCase.execute(
                                uiState.value.repeatedPassword,
                                uiState.value.password
                            ).successful
                )
            }



            is RegisterPageEvent.PasswordChanged -> {
                val newPasswordValidation = validateNewPasswordUseCase.execute(
                    event.password,
                    _uiState.value.repeatedPassword
                )
                val repeatedPasswordValidation = validateRepeatedPasswordUseCase.execute(
                    event.password,
                    _uiState.value.repeatedPassword
                )

                _uiState.value = _uiState.value.copy(
                    password = event.password,
                    passwordError = newPasswordValidation.errorMessage,
                    repeatedPasswordError = if (newPasswordValidation.successful) null else _uiState.value.repeatedPasswordError,
                    isInputValid = newPasswordValidation.successful &&
                            repeatedPasswordValidation.successful &&
                            validateFullNameUseCase.execute(_uiState.value.fullName).successful &&
                            validateEmailUseCase.execute(_uiState.value.email).successful
                )
            }

            is RegisterPageEvent.RepeatedPasswordChanged -> {
                val newPasswordValidation = validateNewPasswordUseCase.execute(
                    _uiState.value.password,
                    event.repeatedPassword
                )
                val repeatedPasswordValidation = validateRepeatedPasswordUseCase.execute(
                    _uiState.value.password,
                    event.repeatedPassword
                )

                _uiState.value = _uiState.value.copy(
                    repeatedPassword = event.repeatedPassword,
                    repeatedPasswordError = repeatedPasswordValidation.errorMessage,
                    passwordError = if (repeatedPasswordValidation.successful) null else _uiState.value.passwordError,
                    isInputValid = newPasswordValidation.successful &&
                            repeatedPasswordValidation.successful &&
                            validateFullNameUseCase.execute(_uiState.value.fullName).successful &&
                            validateEmailUseCase.execute(_uiState.value.email).successful
                )
            }


            is RegisterPageEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }
        }
    }


}