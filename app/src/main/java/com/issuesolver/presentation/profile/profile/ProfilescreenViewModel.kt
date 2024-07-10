package com.issuesolver.presentation.profile.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.usecase.profile.backend.GetMeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenState())
    val uiState: StateFlow<ProfileScreenState> = _uiState.asStateFlow()
    private val _profileState: MutableStateFlow<State?> =  MutableStateFlow(null)
    val profileState: StateFlow<State?> = _profileState

    init {
        fetchProfile()
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
                            fullName = resource.data?.data?.fullName,
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
}
