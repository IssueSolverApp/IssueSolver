package com.issuesolver.presentation.login.qeydiyyat_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import com.issuesolver.domain.useCase.ConfirmOtpUseCase
import com.issuesolver.domain.useCase.RegisterUseCase
import com.issuesolver.domain.useCase.ResendOtpUseCase
import com.issuesolver.domain.useCase.login.ValidateEmailUseCase
import com.issuesolver.domain.useCase.login.ValidateFullNameUseCase
import com.issuesolver.domain.useCase.login.ValidatePasswordUseCase
import com.issuesolver.domain.useCase.login.ValidateRepeatedPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmOtpViewModel@Inject constructor(
    private val confirmOtpUseCase: ConfirmOtpUseCase,
    private val resendOtpUseCase: ResendOtpUseCase
): ViewModel() {

    private val _confirmOtpState = MutableStateFlow<Resource<String?>>(Resource.Loading())
    val confirmOtpState: StateFlow<Resource<String?>> = _confirmOtpState

    private val _resendOtpState = MutableStateFlow<Resource<String?>>(Resource.Loading())
    val resendOtpState: StateFlow<Resource<String?>> = _resendOtpState

    fun register(request: RequestOtp) {
        viewModelScope.launch {
            confirmOtpUseCase(request).collect{
                _confirmOtpState.value = it
            }
        }
    }

    fun resendOtp(request: ResendOtpModel) {
        viewModelScope.launch {
            resendOtpUseCase(request).collect{

            }
        }
    }
}