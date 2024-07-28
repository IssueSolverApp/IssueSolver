package com.issuesolver.presentation.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.category.CategoryData
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.organization.OrganizationData
import com.issuesolver.domain.usecase.home.backend.FilterUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetCategoryUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetOrganizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getOrganizationUseCase: GetOrganizationUseCase
) : ViewModel() {

    private val _categoryState: MutableStateFlow<State?> = MutableStateFlow(null)
    val categoryState: StateFlow<State?> = _categoryState.asStateFlow()

    val category: MutableStateFlow<List<CategoryData>?> = MutableStateFlow(null)

    private val _organizationState: MutableStateFlow<State?> = MutableStateFlow(null)
    val organizationState: StateFlow<State?> = _organizationState.asStateFlow()

    val organization: MutableStateFlow<List<OrganizationData>?> = MutableStateFlow(null)

    private val _statusState: MutableStateFlow<State?> = MutableStateFlow(null)
    val statusState: StateFlow<State?> = _statusState.asStateFlow()

    private val _daysState: MutableStateFlow<State?> = MutableStateFlow(null)
    val daysState: StateFlow<State?> = _daysState.asStateFlow()

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _selectedOrganization = MutableStateFlow("")
    val selectedOrganization: StateFlow<String> = _selectedOrganization.asStateFlow()

    private val _selectedStatus = MutableStateFlow("")
    val selectedStatus: StateFlow<String> = _selectedStatus.asStateFlow()

    private val _selectedDays = MutableStateFlow("")
    val selectedDays: StateFlow<String> = _selectedDays.asStateFlow()

    private val _filterParams = MutableStateFlow(FilterParams("", "", "", ""))
    private val _requestsState: MutableStateFlow<PagingData<FilterData>> = MutableStateFlow(PagingData.empty())
    val requestsState: StateFlow<PagingData<FilterData>> get() = _requestsState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                _selectedCategory,
                _selectedOrganization,
                _selectedStatus,
                _selectedDays
            ) { category, organization, status, days ->
                FilterParams(status, category, organization, days)
            }.distinctUntilChanged().collect {
                fetchFilteredRequests(it.status, it.categoryName, it.organizationName, it.days)
            }
        }
    }

    fun applyFilters(status: String, categoryName: String, organizationName: String, days: String) {
        _selectedStatus.value = status
        _selectedCategory.value = categoryName
        _selectedOrganization.value = organizationName
        _selectedDays.value = days
        // You may want to trigger the request fetching based on these new values here
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun selectOrganization(organization: String) {
        _selectedOrganization.value = organization
    }

    fun selectStatus(status: String) {
        _selectedStatus.value = status
    }

    fun selectDays(days: String) {
        _selectedDays.value = days
    }

    fun fetchFilteredRequests(
        status: String = "",
        categoryName: String = "",
        organizationName: String = "",
        days: String = ""
    ) {
        viewModelScope.launch {
            filterUseCase(status, categoryName, organizationName, days)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _requestsState.value = it
                }
        }
    }

    fun getOrganization() {
        viewModelScope.launch {
            getOrganizationUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> _organizationState.emit(State.loading())
                    is Resource.Error -> _organizationState.emit(State.error(resource.message))
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
                    is Resource.Loading -> _categoryState.emit(State.loading())
                    is Resource.Error -> _categoryState.emit(State.error(resource.message))
                    is Resource.Success -> {
                        _categoryState.emit(State.success())
                        category.value = resource.data?.data
                    }
                }
            }
        }
    }

    data class FilterParams(
        val status: String,
        val categoryName: String,
        val organizationName: String,
        val days: String
    )
}