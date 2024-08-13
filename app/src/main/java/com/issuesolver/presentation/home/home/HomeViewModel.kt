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
import com.issuesolver.domain.usecase.home.backend.GetFilteredResultsUseCase
import com.issuesolver.domain.usecase.myrequestusecase.GetRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.LikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.RemoveLikeUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetCategoryUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetOrganizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        _requestsState.value = PagingData.empty()
    }




    private val _categoryState: MutableStateFlow<State?> = MutableStateFlow(null)
    val categoryState: StateFlow<State?> = _categoryState.asStateFlow()

    val category: MutableStateFlow<List<CategoryData>?> = MutableStateFlow(null)

    private val _organizationState: MutableStateFlow<State?> = MutableStateFlow(null)
    val organizationState: StateFlow<State?> = _organizationState.asStateFlow()

    val organization: MutableStateFlow<List<OrganizationData>?> = MutableStateFlow(null)


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
                    is Resource.Loading -> _organizationState.emit(State.loading())
                    is Resource.Error -> _organizationState.emit(State.error(resource.message))
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
                    is Resource.Loading -> _categoryState.emit(State.loading())
                    is Resource.Error -> _categoryState.emit(State.error(resource.message))
                    is Resource.Success -> {
                        cachedCategory = resource.data?.data
                        _categoryState.emit(State.success())
                        category.value = cachedCategory
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
                    }
                    is Resource.Error -> {
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
                    }
                    is Resource.Error -> {
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

