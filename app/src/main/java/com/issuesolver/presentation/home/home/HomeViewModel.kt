package com.issuesolver.presentation.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.domain.usecase.home.backend.FilterUseCase
import com.issuesolver.domain.usecase.home.backend.RequestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val requestUseCase: RequestUseCase,
    private val filterUseCase: FilterUseCase,

    ) : ViewModel(){

    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items = _items.asStateFlow()

    init {
        fetchRequests()
    }

    private fun fetchRequests(page: Int = 1, size: Int = 10) {
        viewModelScope.launch {
            requestUseCase(page, size).collect { resource ->
                when (resource) {
                    is Resource.Error -> TODO()
                    is Resource.Loading -> TODO()
                    is Resource.Success -> TODO()
                }
            }
        }
    }
       private fun fetchFileterdRequests(
            status: String,
            categoryName: String,
            organizationName: String,
            days: String
        ) {
            viewModelScope.launch {
                filterUseCase(status, categoryName, organizationName, days).collect { resource ->
                    when (resource) {
                        is Resource.Error -> TODO()
                        is Resource.Loading -> TODO()
                        is Resource.Success -> TODO()
                    }
                }
            }
        }

        fun loadItems(status: String = "", categoryName: String = "", organizationName: String = "", days: String = "") {
        viewModelScope.launch {
            fetchFileterdRequests(status, categoryName, organizationName, days)
        } }
    }