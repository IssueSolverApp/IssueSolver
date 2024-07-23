package com.issuesolver.presentation.home.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.category.CategoryData
import com.issuesolver.domain.entity.networkModel.organization.OrganizationData
import com.issuesolver.domain.usecase.home.backend.FilterUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetCategoryUseCase
import com.issuesolver.domain.usecase.newrequestusecase.GetOrganizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getOrganizationUseCase: GetOrganizationUseCase


    ) : ViewModel(){


    //--------------------------------------------------------------------------------------------------
    private var _categoryState: MutableStateFlow<State?> = MutableStateFlow(null)
    val categoryState: StateFlow<State?> = _categoryState.asStateFlow()

    val category: MutableStateFlow<List<CategoryData>?> = MutableStateFlow(null)

    //--------------------------------------------------------------------------------------------------
    private var _organizationState: MutableStateFlow<State?> = MutableStateFlow(null)
    val organizationState: StateFlow<State?> = _organizationState.asStateFlow()

    val organization: MutableStateFlow<List<OrganizationData>?> = MutableStateFlow(null)
//--------------------------------------------------------------------------------------------------


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
}