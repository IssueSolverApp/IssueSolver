package com.issuesolver.presentation.newrequest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.NewRequest
import com.issuesolver.domain.entity.networkModel.category.CategoryData
import com.issuesolver.domain.entity.networkModel.organization.OrganizationData
import com.issuesolver.domain.usecase.newrequestusecase.GetCategoryUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetOrganizationUseCase
import com.issuesolver.domain.usecase.newrequestusecase.NewRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestScreenViewModel @Inject constructor(
    private val newRequestUseCase: NewRequestUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getOrganizationUseCase: GetOrganizationUseCase
) : ViewModel() {

    private var _newRequestState: MutableStateFlow<State?> = MutableStateFlow(null)
    val newRequestState: StateFlow<State?> = _newRequestState.asStateFlow()

    //--------------------------------------------------------------------------------------------------
    private var _categoryState: MutableStateFlow<State?> = MutableStateFlow(null)
    val categoryState: StateFlow<State?> = _categoryState.asStateFlow()

    val category: MutableStateFlow<List<CategoryData>?> = MutableStateFlow(null)

    //--------------------------------------------------------------------------------------------------
    private var _organizationState: MutableStateFlow<State?> = MutableStateFlow(null)
    val organizationState: StateFlow<State?> = _organizationState.asStateFlow()

    val organization: MutableStateFlow<List<OrganizationData>?> = MutableStateFlow(null)
//--------------------------------------------------------------------------------------------------


    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location

    fun updateLocation(newLocation: String) {
        _location.value = newLocation
    }

    init {
        getCategory()
        getOrganization()
    }


    fun getOrganization() {
        viewModelScope.launch {
            getOrganizationUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _organizationState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _organizationState.emit(State.error(resource.message))
                    }

                    is Resource.Success -> {
                        _organizationState.emit(State.success())
                        organization.value = resource.data?.data

                    }


                }

            }
        }
    }


    fun getCategory() {
        viewModelScope.launch {
            getCategoryUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _categoryState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _categoryState.emit(State.error(resource.message))
                    }

                    is Resource.Success -> {
                        _categoryState.emit(State.success())
                        category.value = resource.data?.data

                    }


                }

            }
        }
    }


    fun sendRequest(categoryName: String, request: NewRequest) {
        //Loading
        viewModelScope.launch {
            newRequestUseCase(categoryName, request).collect { resource ->

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