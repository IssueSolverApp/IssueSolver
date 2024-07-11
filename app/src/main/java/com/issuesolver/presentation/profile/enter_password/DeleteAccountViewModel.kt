package com.issuesolver.presentation.profile.enter_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.profile.DeleteAccountRequest
import com.issuesolver.domain.usecase.profile.backend.UpdatePasswordUseCase
import com.issuesolver.domain.usecase.profile.local.PreviousPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAccountViewModel @Inject constructor(
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val previousPasswordUseCase: PreviousPasswordUseCase,

    ) : ViewModel() {

    private val _uiState = MutableStateFlow(DeleteAccountState())
    val uiState: StateFlow<DeleteAccountState> = _uiState.asStateFlow()
    private val _profileState: MutableStateFlow<State?> =  MutableStateFlow(null)
    val profileState: StateFlow<State?> = _profileState

    fun deleteAccount(request: DeleteAccountRequest) {
        viewModelScope.launch {
            updatePasswordUseCase(request).collect { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _profileState.emit(State.loading())
                    }
                    is Resource.Success -> {
                        _profileState.emit(State.success())
                    }
                    is Resource.Error -> {
                        _profileState.emit(State.error(resource.message))
//                        _uiState.value = uiState.value.copy(passwordError = resource.message)

                    }
                }
            }
        }
    }

    fun handleEvent(event: DeleteAccountEvent) {
        when (event) {
            is DeleteAccountEvent.PasswordChanged -> {
                val result = previousPasswordUseCase.execute(
                    event.password)
                _uiState.value = _uiState.value.copy(
                    password = event.password,
                    passwordError = result.errorMessage,
                    isInputValid = result.successful
                )
            }


            is DeleteAccountEvent.Submit -> {
                if (uiState.value.isInputValid) {
                }
            }
        }
    }

}
