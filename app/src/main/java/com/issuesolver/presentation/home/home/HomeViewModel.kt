package com.issuesolver.presentation.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.category.CategoryData
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.home.FilterResponseModel
import com.issuesolver.domain.entity.networkModel.organization.OrganizationData
import com.issuesolver.domain.usecase.home.backend.GetFilteredResultsUseCase
import com.issuesolver.domain.usecase.myrequestusecase.DeleteRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.GetRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.LikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.RemoveLikeUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetCategoryUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetOrganizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
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

    //----------------------------------------------------------------------------------------------

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _selectedOrganization = MutableStateFlow("")
    val selectedOrganization: StateFlow<String> = _selectedOrganization.asStateFlow()

    private val _selectedStatus = MutableStateFlow("")
    val selectedStatus: StateFlow<String> = _selectedStatus.asStateFlow()

    private val _selectedDays = MutableStateFlow("")
    val selectedDays: StateFlow<String> = _selectedDays.asStateFlow()

    //----------------------------------------------------------------------------------------------


    private val _filterParams = MutableStateFlow(FilterParams("", "", "", ""))
    private val _requestsState: MutableStateFlow<PagingData<FilterData>> = MutableStateFlow(PagingData.empty())
    val requestsState: StateFlow<PagingData<FilterData>> get() = _requestsState.asStateFlow()

//    init {
//        viewModelScope.launch {
//            combine(
//                _selectedCategory,
//                _selectedOrganization,
//                _selectedStatus,
//                _selectedDays
//            ) { category, organization, status, days ->
//                FilterParams(status, category, organization, days)
//            }.distinctUntilChanged().collect {
//                fetchFilteredRequests(it.status, it.categoryName, it.organizationName, it.days)
//            }
//        }
//    }

//    fun applyFilters(status: String, categoryName: String, organizationName: String, days: String) {
//        _selectedStatus.value = status
//        _selectedCategory.value = categoryName
//        _selectedOrganization.value = organizationName
//        _selectedDays.value = days
//        // You may want to trigger the request fetching based on these new values here
//    }

    //----------------------------------------------------------------------------
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
    //----------------------------------------------------------------------------

//    fun fetchFilteredRequests(
//        status: String = "",
//        categoryName: String = "",
//        organizationName: String = "",
//        days: String = ""
//    ) {
//        viewModelScope.launch {
//            filterUseCase(status, categoryName, organizationName, days)
//                .distinctUntilChanged()
//                .cachedIn(viewModelScope)
//                .collect {
//                    _requestsState.value = it
//                }
//        }
//    }

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


    private val _selectedFilters = MutableStateFlow(FilterParams("", "", "", ""))
    val selectedFilters: StateFlow<FilterParams> = _selectedFilters

    fun applyFilters2(status: String, categoryName: String, organizationName: String, days: String) {
        _selectedFilters.value = FilterParams(status, categoryName, organizationName, days)
        _requestsState.value = PagingData.empty() // Очистить данные перед загрузкой новых

        fetchFilteredRequests2()
    }


     fun fetchFilteredRequests2() {
        viewModelScope.launch {
            filterUseCase.invoke(
                _selectedFilters.value.status,
                _selectedFilters.value.categoryName,
                _selectedFilters.value.organizationName,
                _selectedFilters.value.days
            ).cachedIn(viewModelScope).collect {
                _requestsState.value = it
            }
        }
    }

}*/


@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getFilteredResultsUseCase: GetFilteredResultsUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getOrganizationUseCase: GetOrganizationUseCase,
    private val likeUseCase: LikeUseCase,
    private val removeLikeUseCase: RemoveLikeUseCase,
    private val getRequestByIdUseCase: GetRequestByIdUseCase
) : ViewModel() {

    private val _filterParams = MutableStateFlow(FilterParams())
    val filterParams: StateFlow<FilterParams> = _filterParams.asStateFlow()

    private val _requestsState: MutableStateFlow<PagingData<FilterData>> = MutableStateFlow(PagingData.empty())
    val requestsState: StateFlow<PagingData<FilterData>> = _requestsState.asStateFlow()

    val filteredResults: Flow<PagingData<FilterData>> = _filterParams
        .flatMapLatest { params ->
            getFilteredResultsUseCase.execute(
                status = params.status,
                categoryName = params.categoryName,
                organizationName = params.organizationName,
                days = params.days
            ).flow.cachedIn(viewModelScope)
        }

    init {
        viewModelScope.launch {
            filteredResults.collect {
                _requestsState.value = it
            }
        }
    }

    fun updateFilterParams(params: FilterParams) {
        _filterParams.value = params
    }

    fun applyFilters2(status: String, categoryName: String, organizationName: String, days: String) {
        _filterParams.value = FilterParams(status, categoryName, organizationName, days)
        _requestsState.value = PagingData.empty() // Очистить данные перед загрузкой новых
    }




    private val _categoryState: MutableStateFlow<State?> = MutableStateFlow(null)
    val categoryState: StateFlow<State?> = _categoryState.asStateFlow()

    val category: MutableStateFlow<List<CategoryData>?> = MutableStateFlow(null)

    private val _organizationState: MutableStateFlow<State?> = MutableStateFlow(null)
    val organizationState: StateFlow<State?> = _organizationState.asStateFlow()

    val organization: MutableStateFlow<List<OrganizationData>?> = MutableStateFlow(null)


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

    private val _likeStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val likeStates: StateFlow<Map<Int, Boolean>> get() = _likeStates.asStateFlow()


    private val _isLiked: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val isLiked: StateFlow<State> = _isLiked

    fun sendLike(requestId: Int?) {
        viewModelScope.launch {
            likeUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state if needed
                    }
                    is Resource.Error -> {
                        // Handle error state if needed
                    }
                    is Resource.Success -> {
                        _likeStates.update { it.toMutableMap().apply { put(requestId ?: -1, true) } }
                        _isLiked.emit(State.success())
                    }
                }
            }
        }
    }

    fun removeLike(requestId: Int?) {
        viewModelScope.launch {
            removeLikeUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state if needed
                    }
                    is Resource.Error -> {
                        // Handle error state if needed
                    }
                    is Resource.Success -> {
                        _likeStates.update { it.toMutableMap().apply { put(requestId ?: -1, false) } }
                    }
                }
            }
        }
    }
    private var _requestByIdState: MutableStateFlow<State?> = MutableStateFlow(null)
    val requestByIdState: StateFlow<State?> = _requestByIdState.asStateFlow()

    val requestById: MutableStateFlow<FilterData?> = MutableStateFlow(null)


    fun getRequestById(requestId: Int?) {
        viewModelScope.launch {
            getRequestByIdUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _requestByIdState.emit(State.loading())
                    }
                    is Resource.Error -> {
                        _requestByIdState.emit(State.error(message = resource.message))
                    }
                    is Resource.Success -> {
                        _requestByIdState.emit(State.success())
                        requestById.value = resource.data?.data

                    }
                }
            }
        }
    }




}

data class FilterParams(
    val status: String = "",
    val categoryName: String = "",
    val organizationName: String = "",
    val days: String = ""
)

