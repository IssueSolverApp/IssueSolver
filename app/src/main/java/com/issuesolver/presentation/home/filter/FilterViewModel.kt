//package com.issuesolver.presentation.home.filter
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.issuesolver.common.Resource
//import com.issuesolver.common.State
//import com.issuesolver.domain.entity.networkModel.home.CategoryData2
//import com.issuesolver.domain.usecase.home.backend.FilterUseCase
//import com.issuesolver.domain.usecase.profile.backend.DeleteAccountUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import javax.inject.Inject
//
//@HiltViewModel
//
//class FilterViewModel @Inject constructor(
//    private val filterUseCase: FilterUseCase,
//
//
//    ) : ViewModel(){
//    private var _organizationState: MutableStateFlow<State?> = MutableStateFlow(null)
//    val organizationState: StateFlow<State?> = _organizationState.asStateFlow()
//
//    val organization: MutableStateFlow<List<OrganizationData>?> = MutableStateFlow(null)
//
//
//    private var _categoryState: MutableStateFlow<State?> = MutableStateFlow(null)
//    val categoryState: StateFlow<State?> = _categoryState.asStateFlow()
//
//    val category: MutableStateFlow<List<CategoryData2>?> = MutableStateFlow(null)
//
//
//    fun getCategory() {
//        viewModelScope.launch {
//            filterUseCase.invoke().collect { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        _categoryState.emit(State.loading())
//                    }
//
//                    is Resource.Error -> {
//                        _categoryState.emit(State.error(resource.message))
//                    }
//
//                    is Resource.Success -> {
//                        _categoryState.emit(State.success())
//                        category.value = resource.data?.data
//
//                    }
//
//
//                }
//
//            }
//        }
//    }
//}