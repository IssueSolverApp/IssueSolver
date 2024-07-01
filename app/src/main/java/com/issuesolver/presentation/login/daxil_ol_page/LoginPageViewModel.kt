package com.issuesolver.presentation.login.daxil_ol_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.entity.networkModel.LoginRequest
import com.issuesolver.domain.entity.networkModel.LoginResponse
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.useCase.SignInUseCase
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import com.issuesolver.domain.useCase.login.LoginUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(

//    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginUseCase: LoginUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginPageState())
    val uiState: StateFlow<LoginPageState> = _uiState.asStateFlow()

    private val _signInState = MutableStateFlow<Resource<LoginResponse?>>(Resource.Loading())
    val signInState: StateFlow<Resource<LoginResponse?>> = _signInState

    fun signIn(request: LoginRequest) {
        viewModelScope.launch {
            signInUseCase(request).collect { resource ->
                _signInState.value = resource
            }
        }
    }

    fun handleEvent(event: LoginPageEvent) {
        when (event) {
            is LoginPageEvent.EmailChanged -> {
                val result = loginUseCase.execute(event.email)
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
                    isInputValid = result.successful && loginUseCase.execute(uiState.value.email).successful
                )
            }
            is LoginPageEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }
        }
    }
}
