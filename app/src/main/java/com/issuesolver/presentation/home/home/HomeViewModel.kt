package com.issuesolver.presentation.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.home.backend.FilterUseCase
import com.issuesolver.domain.usecase.home.backend.RequestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val requestUseCase: RequestUseCase,
    private val filterUseCase: FilterUseCase,

    ) : ViewModel(){

    private val _filterData = MutableStateFlow<List<FilterData>?>(null)
    val filterData: StateFlow<List<FilterData>?> = _filterData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchItems()
    }

    fun fetchItems(page: Int = 1, size: Int = 10) {
        viewModelScope.launch {
            requestUseCase(page, size).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _isLoading.value = true
                    is Resource.Success -> {
                        _isLoading.value = false
                        _filterData.value = resource.data?.data
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = resource.message
                    }
                }
            }
        }
    }

    fun applyFilter(status: String, categoryName: String, organizationName: String, days: String) {
        viewModelScope.launch {
            filterUseCase(status, categoryName, organizationName, days).collect { resource ->
                when (resource) {
                    is Resource.Loading -> _isLoading.value = true
                    is Resource.Success -> {
                        _isLoading.value = false
                        _filterData.value = resource.data?.data
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = resource.message
                    }
                }
            }
        }
    }
    fun loadItems(){



    }
}