package com.issuesolver.presentation.login.daxil_ol_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.domain.usecase.login.ValidateEmailUseCase
import com.issuesolver.domain.usecase.login.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.login.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class daxil_ol_page_viewModel@Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCaseUseCase: ValidatePasswordUseCase,
): ViewModel()  {


    var daxil_ol_page_state by mutableStateOf(daxil_ol_page_state())
        private set

    fun onEmailInputChange(newValue: String){
        daxil_ol_page_state = daxil_ol_page_state.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String){
        daxil_ol_page_state = daxil_ol_page_state.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformation(){
        daxil_ol_page_state = daxil_ol_page_state.copy(isPasswordShown = !daxil_ol_page_state.isPasswordShown)
    }

    fun onLoginClick(){
        daxil_ol_page_state = daxil_ol_page_state.copy(isLoading = true)
        viewModelScope.launch {
            daxil_ol_page_state = try{
                val ValidationResult = LoginRep.login(
                    email = loginState.emailInput,
                    password = loginState.passwordInput
                )
                loginState.copy(isSuccessfullyLoggedIn = loginResult)
            }catch(e: Exception){
                loginState.copy(errorMessageLoginProcess = "Could not login")
            }finally {
                loginState = loginState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType){
        loginState = when(type) {
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }

            LoginInputValidationType.NoEmail -> {
                loginState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }

            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }}

}
