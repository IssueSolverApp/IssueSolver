package com.issuesolver.presentation.login.daxil_ol_verification_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.login.RequestOtp
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import com.issuesolver.domain.usecase.login.backend.OtpTrustUseCase
import com.issuesolver.domain.usecase.login.backend.ResendOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationCodePageViewModel @Inject constructor(
    private val otpTrustUseCase: OtpTrustUseCase,
    private val resendOtpUseCase: ResendOtpUseCase
) : ViewModel() {

    private val _otpTrustState: MutableStateFlow<State?> = MutableStateFlow(null)
    val otpTrustState: StateFlow<State?> = _otpTrustState


    private val _uiState = MutableStateFlow(VerificationCodePageState())
    val uiState: StateFlow<VerificationCodePageState> = _uiState.asStateFlow()


    private val _resendOtpState: MutableStateFlow<State?> = MutableStateFlow(null)
    val resendOtpState: StateFlow<State?> = _resendOtpState


    fun otpTrust(request: RequestOtp) {
        viewModelScope.launch {
            otpTrustUseCase(request).collect {
                when (it) {
                    is Resource.Loading -> {
                        _otpTrustState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _otpTrustState.emit(State.error(message = it.message))
                        _uiState.value = uiState.value.copy(otpValueError = it.message)

                    }

                    is Resource.Success -> {
                        _otpTrustState.emit(State.success())
                    }


                }
            }
        }
    }

    fun resendOtp(request: ResendOtpModel) {
        viewModelScope.launch {
            resendOtpUseCase(request).collect {
                when (it) {
                    is Resource.Loading -> {
                        _resendOtpState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _resendOtpState.emit(State.error(message = it.message))
                    }

                    is Resource.Success -> {
                        _resendOtpState.emit(State.success())
                    }
                }
            }
        }
    }


    fun clearOtpTrustState() {
        _otpTrustState.value = null
    }


}