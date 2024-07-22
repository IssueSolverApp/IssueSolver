package com.issuesolver.presentation.newrequest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.NewRequest
import com.issuesolver.domain.usecase.newrequestusecase.NewRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestScreenViewModel @Inject constructor(
    private val requestRepository: NewRequestUseCase
) : ViewModel() {

    private var _newRequestState: MutableStateFlow<State?> = MutableStateFlow(null)
    val newRequestState: StateFlow<State?> = _newRequestState.asStateFlow()

    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location

    fun updateLocation(newLocation: String) {
        _location.value = newLocation
    }



    fun sendRequest(categoryName: String, request: NewRequest) {
        //Loading
        viewModelScope.launch {
            requestRepository(categoryName, request).collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        _newRequestState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _newRequestState.emit(State.error(resource.message))
                        //_uiState.value = uiState.value.copy(emailError = resource.message)
                    }

                    is Resource.Success -> {
                        _newRequestState.emit(State.success())

                    }


                }
            }
        }
    }



    var text1 by mutableStateOf("")
    var text2 by mutableStateOf("")


    val isButtonEnabled: Boolean
        get() = text1.length >= 5 && text2.length >= 10

    fun onLocationTextChanged(textFieldIndex: Int, newText: String) {
        if (newText.length <= 150) {
            when (textFieldIndex) {
                1 -> text1 = newText
            }
        }
    }


    fun onDescriptionTextChanged(textFieldIndex: Int, newText: String) {
        if (newText.length <= 500) {
            when (textFieldIndex) {
                1 -> text1 = newText
            }
        }
    }

    







}