package com.issuesolver.presentation.newrequest

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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private val _selectedOrganization = MutableStateFlow("")
    val selectedOrganization: StateFlow<String> = _selectedOrganization

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> = _isFormValid


//--------------------------------------------------------------------------------------------------

    init {
        combine(
            _location,
            _selectedOrganization,
            _selectedCategory,
            _description
        ) { location, organization, category, description ->
            location.length>4 && category.isNotEmpty() && description.length>9 && organization.isNotEmpty()
        }.onEach { isValid ->
            _isFormValid.value = isValid
        }.launchIn(viewModelScope)
    }






//--------------------------------------------------------------------------------------------------
    fun updateLocation(newLocation: String) {
        _location.value = newLocation
    }

    fun updateDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun selectOrganization(organization: String) {
        _selectedOrganization.value = organization
    }

    //--------------------------------------------------------------------------------------------------

    private var cachedOrganization: List<OrganizationData>? = null

    fun getOrganization() {

        if (cachedOrganization != null) {
            _organizationState.value = State.success()
            organization.value = cachedOrganization
            return
        }

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
                        cachedOrganization = resource.data?.data // Кешируем данные
                        _organizationState.emit(State.success())
                        organization.value = cachedOrganization

                    }


                }

            }
        }
    }


    private var cachedCategory: List<CategoryData>? = null

    fun getCategory() {

        if (cachedCategory != null) {
            _categoryState.value = State.success()
            category.value = cachedCategory
            return
        }

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
                        cachedCategory = resource.data?.data
                        _categoryState.emit(State.success())
                        category.value = cachedCategory

                    }

                }

            }
        }
    }


    fun sendRequest() {
        viewModelScope.launch {
            newRequestUseCase(
                selectedCategory.value, NewRequest(
                    address = location.value,
                    description = description.value,
                    organizationName = selectedOrganization.value

                )
            ).collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        _newRequestState.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _newRequestState.emit(State.error(resource.message))
                    }

                    is Resource.Success -> {
                        _newRequestState.emit(State.success())

                    }


                }
            }
        }
    }

    fun resetFields() {
        _location.value = ""
        _description.value = ""
    }
}