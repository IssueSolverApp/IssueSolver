package com.issuesolver.presentation.login.qeydiyyat_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.useCase.RegisterUseCase
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
): ViewModel() {


    private val _registerState = MutableStateFlow<Resource<String?>>(Resource.Loading())
    val registerState: StateFlow<Resource<String?>> = _registerState


    fun register(request: RegisterRequestModel) {
        viewModelScope.launch {
            registerUseCase(request).collect { resource ->
                _registerState.value = resource
            }
        }
    }



}