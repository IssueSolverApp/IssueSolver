package com.issuesolver.presentation.myrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.myrequestusecase.DeleteRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.LikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.MyRequestUseCase
import com.issuesolver.domain.usecase.myrequestusecase.RemoveLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRequestViewModel @Inject constructor(
    private val myRequestUseCase: MyRequestUseCase,
    private val likeUseCase: LikeUseCase,
    private val removeLikeUseCase: RemoveLikeUseCase,
    private val deleteRequestByIdUseCase: DeleteRequestByIdUseCase
) : ViewModel() {

    val myRequests = myRequestUseCase().cachedIn(viewModelScope)

    private val _moviesState: MutableStateFlow<PagingData<FilterData>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: StateFlow<PagingData<FilterData>> get() = _moviesState

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        myRequestUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }

    private val _likeStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val likeStates: StateFlow<Map<Int, Boolean>> get() = _likeStates.asStateFlow()

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

    fun deleteRequestById(requestId: Int) {
        viewModelScope.launch {
            deleteRequestByIdUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state if needed
                    }
                    is Resource.Error -> {
                        // Handle error state if needed
                    }
                    is Resource.Success -> {
                        _moviesState.update { pagingData ->
                            pagingData.filter { it.requestId != requestId }
                        }
                        _likeStates.update { it - requestId }
                    }
                }
            }
        }
    }
}
