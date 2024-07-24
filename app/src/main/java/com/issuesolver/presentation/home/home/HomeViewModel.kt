package com.issuesolver.presentation.home.home

import android.content.ClipData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
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

    private var _items = MutableStateFlow<List<ClipData.Item>?>(null)
    val items: StateFlow<List<ClipData.Item>?> = _items.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchItems()
    }

    fun fetchItems(filter: String? = null) {
        viewModelScope.launch {
        }
    }
    fun loadItems(){}
}