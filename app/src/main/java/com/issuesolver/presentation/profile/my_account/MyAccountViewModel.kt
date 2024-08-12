package com.issuesolver.presentation.profile.my_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.profile.UpdateFullNameRequest
import com.issuesolver.domain.usecase.profile.backend.GetMeUseCase
import com.issuesolver.domain.usecase.profile.backend.UpdateFullNameUseCase
import com.issuesolver.domain.usecase.profile.local.FullNameUseCase
import com.issuesolver.presentation.login.daxil_ol_page_email.VerificationCodePageEvent
import com.issuesolver.presentation.profile.profile.ProfileScreenState
import com.issuesolver.presentation.profile.profile.ProfileUpdateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAccountViewModel @Inject constructor(
    private val updateFullNameUseCase: UpdateFullNameUseCase,
    private val getMeUseCase: GetMeUseCase,
    private val fullNameUseCase: FullNameUseCase,


    ) : ViewModel() {

    private val _uiState = MutableStateFlow(MyAccountState())
    val uiState: StateFlow<MyAccountState> = _uiState.asStateFlow()
    private val _profileState: MutableStateFlow<State?> =  MutableStateFlow(null)
    val profileState: StateFlow<State?> = _profileState
    private val _profileState2: MutableStateFlow<State?> =  MutableStateFlow(null)
    val profileState2: StateFlow<State?> = _profileState2

    fun clearState() {
        _profileState2.value = null
    }

    init {
        fetchProfile()
    }

    fun updateProfile(request:UpdateFullNameRequest) {
        viewModelScope.launch {
            updateFullNameUseCase(request).collect { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _profileState2.emit(State.loading())
                    }
                    is Resource.Success -> {
                        _profileState2.emit(State.success())
                        request.fullName?.let { ProfileUpdateManager.notifyProfileUpdated(it) }


                    }
                    is Resource.Error -> {
                        _profileState2.emit(State.error(resource.message))
                        _uiState.value = uiState.value.copy(fullNameError = resource.message)

                    }
                }
            }
        }
    }

    fun fetchProfile() {
        viewModelScope.launch {
            getMeUseCase().collect { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _profileState.emit(State.loading())
                    }
                    is Resource.Success -> {
                        _profileState.emit(State.success())
                        _uiState.value = uiState.value.copy(
                            email = resource.data?.data?.email,
                            isLoading = false
                        )


                    }
                    is Resource.Error -> {
                        _profileState.emit(State.error(resource.message))

                    }

                }
            }
        }
    }
    fun handleEvent(event: MyAccountEvent) {
        when (event) {
            is MyAccountEvent.FullNameChanged -> {
                val result = fullNameUseCase.execute(event.fullName)
                _uiState.value = uiState.value.copy(
                    fullName = event.fullName,
                    fullNameError = result.errorMessage,
                    isInputValid = result.successful
                )
            }

            is MyAccountEvent.Submit -> {
                if (uiState.value.isInputValid) {

                }
            }

        }
    }


}
