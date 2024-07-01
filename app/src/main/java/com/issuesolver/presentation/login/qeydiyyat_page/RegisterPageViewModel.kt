package com.issuesolver.presentation.login.qeydiyyat_page
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.useCase.RegisterUseCase
import com.issuesolver.domain.useCase.login.ValidateFullNameUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ValidateNewPasswordUseCase
import com.issuesolver.domain.usecase.login.ValidateRepeatedPasswordUseCase
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

    private var _registerState: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val registerState: StateFlow<State> = _registerState.asStateFlow()

    fun register(request: RegisterRequestModel) {
        //Loading
        viewModelScope.launch {
            registerUseCase(request).collect { resource ->
//                _registerState.value = resource
//                _uiState.value = uiState.value.copy(emailError = resource.message)

                when (resource) {
                    is Resource.Error -> {
                        _registerState.emit(State.error(resource.message))
                        _uiState.value = uiState.value.copy(emailError = resource.message)
                    }

                    is Resource.Success -> {
                        _registerState.emit(State.success())
//                        _uiState.emit(State.success())
//                        burger.data.let {
//                            //val result = it?.let { it1 -> mapper.map(it1) }
//                            burgers.emit(it)
//                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }


    fun handleEvent(event: RegisterPageEvent) {
        when (event) {
            is RegisterPageEvent.EmailChanged -> {
                val result = validateEmailUseCase.execute(event.email)
                _uiState.value = uiState.value.copy(
                    email = event.email,
                    emailError = result.errorMessage,
                    isInputValid = result.successful &&
                            validateNewPasswordUseCase.execute(uiState.value.repeatedPassword, uiState.value.password).successful&&
                            validateFullNameUseCase.execute(uiState.value.fullName).successful &&
                            validateRepeatedPasswordUseCase.execute(uiState.value.repeatedPassword, uiState.value.password).successful
                )
            }

            is RegisterPageEvent.PasswordChanged -> {
                val result = validateNewPasswordUseCase.execute(_uiState.value.repeatedPassword,event.password)
                _uiState.value = uiState.value.copy(
                    password = event.password,
                    passwordError = result.errorMessage,
                    isInputValid = result.successful &&
                            validateRepeatedPasswordUseCase.execute(uiState.value.repeatedPassword, uiState.value.password).successful &&
                            validateFullNameUseCase.execute(uiState.value.fullName).successful &&
                            validateEmailUseCase.execute(uiState.value.email).successful
                )
            }

            is RegisterPageEvent.RepeatedPasswordChanged -> {
                val result = validateRepeatedPasswordUseCase.execute(_uiState.value.password, event.repeatedPassword)
                _uiState.value = _uiState.value.copy(
                    repeatedPassword = event.repeatedPassword,
                    repeatedPasswordError = result.errorMessage,
                    isInputValid = validateNewPasswordUseCase.execute(_uiState.value.repeatedPassword, _uiState.value.password).successful &&
                            result.successful &&
                            validateFullNameUseCase.execute(_uiState.value.fullName).successful &&
                            validateEmailUseCase.execute(_uiState.value.email).successful
                )
            }

            is RegisterPageEvent.FullNameChanged -> {
                val result = validateFullNameUseCase.execute(event.fullName)
                _uiState.value = uiState.value.copy(
                    fullName = event.fullName,
                    fullNameError = result.errorMessage,
                    isInputValid = result.successful &&
                            validateNewPasswordUseCase.execute(uiState.value.repeatedPassword, uiState.value.password).successful &&
                            validateRepeatedPasswordUseCase.execute(uiState.value.repeatedPassword, uiState.value.password).successful &&
                            validateEmailUseCase.execute(uiState.value.email).successful



                )
                
            }


            is RegisterPageEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }
        }
    }



}