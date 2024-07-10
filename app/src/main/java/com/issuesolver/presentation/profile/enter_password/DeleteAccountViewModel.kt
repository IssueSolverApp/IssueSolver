package com.issuesolver.presentation.profile.enter_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.profile.UpdatePasswordRequest
import com.issuesolver.domain.usecase.profile.backend.GetMeUseCase
import com.issuesolver.domain.usecase.profile.backend.UpdatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAccountViewModel @Inject constructor(
    private val updatePasswordUseCase: UpdatePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeleteAccountState())
    val uiState: StateFlow<DeleteAccountState> = _uiState.asStateFlow()
    private val _profileState: MutableStateFlow<State?> =  MutableStateFlow(null)
    val profileState: StateFlow<State?> = _profileState

    fun deletePassword(request:UpdatePasswordRequest) {
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
                        _uiState.value = uiState.value.copy(passwordError = resource.message)

                    }
                }
            }
        }
    }
}
