package com.issuesolver.presentation.profile.profile


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.profile.UpdateFullNameRequest
import com.issuesolver.domain.usecase.profile.backend.GetMeUseCase
import com.issuesolver.domain.usecase.profile.backend.UpdateFullNameUseCase
import com.issuesolver.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase,
    private val updateFullNameUseCase: UpdateFullNameUseCase,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val context: Context

) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenState())
    val uiState: StateFlow<ProfileScreenState> = _uiState.asStateFlow()
    private val _profileState: MutableStateFlow<State?> =  MutableStateFlow(null)
    val profileState: StateFlow<State?> = _profileState

    init {
        viewModelScope.launch {
            ProfileUpdateManager.updateEvents.collect { fullName ->
                updateProfile(UpdateFullNameRequest(fullName))
            }
        }
        fetchProfile()
    }

    fun updateProfile(request:UpdateFullNameRequest) {
        viewModelScope.launch {
            updateFullNameUseCase(request).collect { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _profileState.emit(State.loading())
                    }
                    is Resource.Success -> {
                        _profileState.emit(State.success())
                        fetchProfile()
                    }
                    is Resource.Error -> {
                        _profileState.emit(State.error(resource.message))
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
                            fullName = resource.data?.data?.fullName,
                            email = resource.data?.data?.email,
                            isLoading = false
                        )


                    }
                    is Resource.Error -> {
                    }
                }
            }
        }
    }


    fun logout(){

        sharedPreferences.edit().clear().apply()
        val navIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(navIntent)
    }



}
