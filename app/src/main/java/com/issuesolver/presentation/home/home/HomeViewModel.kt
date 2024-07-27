package com.issuesolver.presentation.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.home.backend.FilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase
) : ViewModel() {

    private val _filterParams = MutableStateFlow(FilterParams("", "", "", ""))

    private val _requestsState: MutableStateFlow<PagingData<FilterData>> = MutableStateFlow(PagingData.empty())
    val requestsState: StateFlow<PagingData<FilterData>> get() = _requestsState.asStateFlow()

    init {
        viewModelScope.launch {
            _filterParams
                .collect { params ->
                    fetchFilteredRequests(params.status, params.categoryName, params.organizationName, params.days)
                }
        }
    }

    private fun fetchFilteredRequests(
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

    fun updateFilterParams(status: String, categoryName: String, organizationName: String, days: String) {
        _filterParams.value = FilterParams(status, categoryName, organizationName, days)
    }

    data class FilterParams(
        val status: String,
        val categoryName: String,
        val organizationName: String,
        val days: String
    )
}
