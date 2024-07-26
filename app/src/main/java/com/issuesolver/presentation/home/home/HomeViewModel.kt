package com.issuesolver.presentation.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.home.backend.FilterUseCase
import com.issuesolver.domain.usecase.home.backend.RequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase
) : ViewModel() {

    private val _requestsState: MutableStateFlow<PagingData<FilterData>> = MutableStateFlow(value = PagingData.empty())
    val requestsState: StateFlow<PagingData<FilterData>> get() = _requestsState

   init {

            fetchFilteredRequests("","","","")

    }

   private  fun fetchFilteredRequests(
        status: String,
        categoryName: String,
        organizationName: String,
        days: String
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


}