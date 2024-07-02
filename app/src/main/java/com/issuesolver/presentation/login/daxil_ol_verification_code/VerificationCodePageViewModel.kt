package com.issuesolver.presentation.login.daxil_ol_verification_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.useCase.OtpTrustUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationCodePageViewModel @Inject constructor(private val otpTrustUseCase: OtpTrustUseCase) :
    ViewModel() {

    private val _otpTrustState = MutableStateFlow<Resource<String?>>(Resource.Loading())
    val otpTrustState: StateFlow<Resource<String?>> = _otpTrustState

    fun otpTrust(request: RequestOtp) {
        viewModelScope.launch {
            otpTrustUseCase(request).collect {
                _otpTrustState.value = it
            }
        }
    }
}