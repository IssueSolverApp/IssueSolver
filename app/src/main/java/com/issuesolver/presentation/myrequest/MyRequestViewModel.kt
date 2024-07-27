package com.issuesolver.presentation.myrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.usecase.myrequestusecase.LikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.MyRequestUseCase
import com.issuesolver.domain.usecase.myrequestusecase.RemoveLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRequestViewModel @Inject constructor(
    private val myRequestUseCase: MyRequestUseCase,
    private val likeUseCase: LikeUseCase,
    private val removeLikeUseCase: RemoveLikeUseCase
) : ViewModel() {

    val myRequests = myRequestUseCase().cachedIn(viewModelScope)

    private val _moviesState: MutableStateFlow<PagingData<FilterData>> =
        MutableStateFlow(value = PagingData.empty())
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


    private var _like: MutableStateFlow<State?> = MutableStateFlow(null)
    val like: StateFlow<State?> = _like.asStateFlow()

    fun sendLike(requestId: Int?) {
        viewModelScope.launch {
            likeUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _like.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _like.emit(State.error(resource.message))
                    }

                    is Resource.Success -> {
                        _like.emit(State.success())

                    }


                }

            }
        }
    }



    private var _removeLike: MutableStateFlow<State?> = MutableStateFlow(null)
    val removeLike: StateFlow<State?> = _removeLike.asStateFlow()
    fun removeLike(requestId: Int?) {
        viewModelScope.launch {
            removeLikeUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _removeLike.emit(State.loading())
                    }

                    is Resource.Error -> {
                        _removeLike.emit(State.error(resource.message))
                    }

                    is Resource.Success -> {
                        _removeLike.emit(State.success())

                    }


                }

            }
        }
    }






















//    private val _favoriteState = MutableStateFlow(false)
//    val favoriteState: StateFlow<Boolean> get() = _favoriteState
//
//    fun toggleFavorite() {
//        viewModelScope.launch {
//            // Replace with your API call logic
//            val newState = !_favoriteState.value
//            if (newState) {
//                // Call API to add to favorites
//            } else {
//                // Call API to remove from favorites
//            }
//            _favoriteState.value = newState
//        }
//    }

}