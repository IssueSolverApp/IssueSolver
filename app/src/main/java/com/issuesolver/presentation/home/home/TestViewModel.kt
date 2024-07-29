package com.issuesolver.presentation.home.home

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.home.backend.RequestUseCase
import com.issuesolver.domain.usecase.home.backend.TestUseCase
import com.issuesolver.domain.usecase.myrequestusecase.GetRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.LikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.RemoveLikeUseCase
import com.issuesolver.presentation.login.qeydiyyat_page.RegisterPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TestViewModel @Inject constructor(private val filterUseCase: TestUseCase,
                                        private val requestUseCase: RequestUseCase,
                                        private val likeUseCase: LikeUseCase,
                                        private val removeLikeUseCase: RemoveLikeUseCase,
                                        private val getRequestByIdUseCase: GetRequestByIdUseCase
    ) : ViewModel() {
    private val _filterParams = MutableStateFlow(FilterParams())
    val filterParams: StateFlow<FilterParams> = _filterParams


    private val _filterResults = MutableStateFlow<PagingData<FilterData>>(PagingData.empty())
    val filterResults: StateFlow<PagingData<FilterData>> = _filterResults

        //private val uiState = MutableLiveData<YourUiState>()

    fun filter(status: String, categoryName: String, organizationName: String, days: String) {
        viewModelScope.launch {
            val params = uiState.value
            val result = filterUseCase.invoke(status, categoryName, organizationName, days)
            //_filterResults.value = result
            result.collect {
                _filterResults.value = it
            }
        }
    }

//    val filterResults: Flow<PagingData<FilterData>> = filterParams.flatMapLatest { params ->
//        filterUseCase(params.status, params.categoryName, params.organizationName, params.days)
//    }.cachedIn(viewModelScope)

    fun setFilterParams(params: FilterParams) {
        _filterParams.value = params
    }



    private val _uiState = MutableStateFlow(Params())
    val uiState: StateFlow<Params> = _uiState.asStateFlow()

    data class FilterParams(
        val status: String = "", //Baxılır
        val categoryName: String = "",
        val organizationName: String = "",
        val days: String = ""
    )

    fun selectStatus(status: String) {
        _selectedStatus.value = status
        _uiState.value = _uiState.value.copy(status = status)
        _filterParams.value = _filterParams.value.copy(status = status)
    }

    private val _selectedCategory = MutableStateFlow("")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _selectedOrganization = MutableStateFlow("")
    val selectedOrganization: StateFlow<String> = _selectedOrganization.asStateFlow()

    private val _selectedStatus = MutableStateFlow("")
    val selectedStatus: StateFlow<String> = _selectedStatus.asStateFlow()

    private val _selectedDays = MutableStateFlow("")
    val selectedDays: StateFlow<String> = _selectedDays.asStateFlow()




    private val _requestResults = MutableStateFlow<PagingData<FilterData>>(PagingData.empty())
    val requestResults: StateFlow<PagingData<FilterData>> = _requestResults


    fun request() {
        viewModelScope.launch {

            val result = requestUseCase.invoke()
            result.collect {
                _requestResults.value = it
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


data class Params(
    val status: String = "",
    val categoryName: String = "",
    val organizationName: String = "",
    val days: String = ""

)