package com.issuesolver.presentation.login.qeydiyyat_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import com.issuesolver.domain.useCase.ConfirmOtpUseCase
import com.issuesolver.domain.useCase.ResendOtpUseCase
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

    private val _confirmOtpState : MutableStateFlow<State> = MutableStateFlow(State.loading())
    val confirmOtpState: StateFlow<State> = _confirmOtpState

    private val _resendOtpState = MutableStateFlow<Resource<String?>>(Resource.Loading())
    val resendOtpState: StateFlow<Resource<String?>> = _resendOtpState

    fun confirmRegister(request: RequestOtp) {
        viewModelScope.launch {
            confirmOtpUseCase(request).collect{
//                _confirmOtpState.value = it
                when(it){
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        _confirmOtpState.emit(State.error(it.message))
                    }
                    is Resource.Success -> {
                        _confirmOtpState.emit(State.success())
                    }
                }
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