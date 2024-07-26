package com.issuesolver.presentation.myrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.login.local.ValidatePasswordUseCase
import com.issuesolver.domain.usecase.myrequestusecase.MyRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyRequestViewModel @Inject constructor(private val myRequestUseCase: MyRequestUseCase):ViewModel() {

    val myRequests = myRequestUseCase().cachedIn(viewModelScope)

    private val _moviesState: MutableStateFlow<PagingData<FilterData>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<FilterData>> get() = _moviesState

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
}