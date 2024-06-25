package com.issuesolver.presentation.login.email_verification_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.entity.networkModel.ForgetPasswordRequest
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ForgetPasswordUseCase
import com.issuesolver.presentation.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailVerificationPageViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val forgetPasswordUseCase: ForgetPasswordUseCase,

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerificationPageState())
    val uiState: StateFlow<EmailVerificationPageState> = _uiState.asStateFlow()

    var navigation: Boolean =false


    fun handleEmailChange(email: String) {
        viewModelScope.launch {
            val result = validateEmailUseCase.execute(email)
            _uiState.value = uiState.value.copy(
                email = email,
                emailError = result.errorMessage,
                isInputValid = result.successful
            )
        }
    }

    fun handleEvent(event: VerificationCodePageEvent) {
        when (event) {
            is VerificationCodePageEvent.EmailChanged -> handleEmailChange(event.email)
            is VerificationCodePageEvent.Submit -> handleSubmit()
        }
    }

    fun handleSubmit() {
        if (uiState.value.isInputValid) {
            viewModelScope.launch {
                forgetPasswordUseCase(ForgetPasswordRequest(uiState.value.email)).collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            navigation=true

                        }
                        is Resource.Error -> {
                            _uiState.value = uiState.value.copy(
                                emailError = resource.message
                            )
                        }
                        is Resource.Loading -> {
                        }
                    }
                }
            }
        }
    }
}






//package com.issuesolver.presentation.login.email_verification_page
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.navigation.NavController
//import com.issuesolver.common.Resource
//import com.issuesolver.domain.entity.networkModel.ForgetPasswordRequest
//import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
//import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
//import com.issuesolver.domain.usecase.login.ForgetPasswordUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharedFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class EmailVerificationPageViewModel @Inject constructor(
//    private val validateEmailUseCase: ValidateEmailUseCase,
//    private val forgetPasswordUseCase: ForgetPasswordUseCase
//
//) : ViewModel() {
//    private val _uiState = MutableStateFlow(EmailVerificationPageState())
//    val uiState: StateFlow<EmailVerificationPageState> = _uiState.asStateFlow()
//
//
//
//    fun forgetPassword(request: ForgetPasswordRequest) {
//        viewModelScope.launch {
//            forgetPasswordUseCase(request).collect { resource ->
//                _uiState.value = resource
//            }
//        }
//    }
//
//
//    fun handleEvent(event: VerificationCodePageEvent) {
//        when (event) {
//            is VerificationCodePageEvent.EmailChanged -> {
//                val result = validateEmailUseCase.execute(event.email)
//                _uiState.value = uiState.value.copy(
//                    email = event.email,
//                    emailError = result.errorMessage,
//                    isInputValid = result.successful
//                )
//            }
//            is VerificationCodePageEvent.Submit -> {
//                if (uiState.value.isInputValid) {
//                    handleSubmit()
//                }
//            }
//
//        }
//    }
//
//    fun handleSubmit() {
//        if (uiState.value.isInputValid) {
//            viewModelScope.launch {
//                forgetPasswordUseCase(uiState.value.email).collect { resource ->
//                    when (resource) {
//                        is Resource.Success -> {
//                        }
//                        is Resource.Error -> {
//                            _uiState.value = uiState.value.copy(
//                                emailError = resource.message
//                            )
//                        }
//                        is Resource.Loading -> {
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
