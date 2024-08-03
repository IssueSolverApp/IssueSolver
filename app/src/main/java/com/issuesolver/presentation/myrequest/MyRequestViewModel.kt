package com.issuesolver.presentation.myrequest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.issuesolver.common.Resource
import com.issuesolver.common.State
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentResponse
import com.issuesolver.domain.entity.networkModel.myrequestmodel.RequestByIdResponseModel
import com.issuesolver.domain.entity.networkModel.organization.OrganizationData
import com.issuesolver.domain.usecase.myrequestusecase.DeleteRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.GetCommentsUseCase
import com.issuesolver.domain.usecase.myrequestusecase.GetRequestByIdUseCase
import com.issuesolver.domain.usecase.myrequestusecase.LikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.MyRequestUseCase
import com.issuesolver.domain.usecase.myrequestusecase.RemoveLikeUseCase
import com.issuesolver.domain.usecase.myrequestusecase.SendCommentUceCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRequestViewModel @Inject constructor(
    private val myRequestUseCase: MyRequestUseCase,
    private val likeUseCase: LikeUseCase,
    private val removeLikeUseCase: RemoveLikeUseCase,
    private val deleteRequestByIdUseCase: DeleteRequestByIdUseCase,
    private val getRequestByIdUseCase: GetRequestByIdUseCase,
    private val getCommentUseCase: GetCommentsUseCase,
    private val sendCommentUseCase: SendCommentUceCase,

    ) : ViewModel() {

    val myRequests = myRequestUseCase().cachedIn(viewModelScope)

    private val _moviesState: MutableStateFlow<PagingData<FilterData>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: StateFlow<PagingData<FilterData>> get() = _moviesState


     suspend fun getMovies() {
        myRequestUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }

    private val _likeStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val likeStates: StateFlow<Map<Int, Boolean>> get() = _likeStates.asStateFlow()


    private val _isLiked: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val isLiked: StateFlow<State> = _isLiked


     private var _liked: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val liked: SharedFlow<Boolean> = _liked


    private val _items = MutableStateFlow<List<FilterData>>(emptyList())
    val items: StateFlow<List<FilterData>> = _items
    fun toggleLike(itemId: Int) {
        val item = _items.value.find { it.requestId == itemId } ?: return

        viewModelScope.launch {
            val result = if (item.likeSuccess!!) {
                likeUseCase.invoke(itemId)
            } else {
                removeLikeUseCase.invoke(itemId)
            }

            result.collect{
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {

                    }
                    is Resource.Success -> {
                        _items.value = _items.value.map { item ->
                            if (item.requestId == itemId) {
                                item.copy(likeSuccess = !item.likeSuccess!!)
                            } else {
                                item
                            }
                        }
                    }
                }
            }

        }
    }

    fun sendLike(requestId: Int?) {
        viewModelScope.launch {
            likeUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state if needed
                    }
                    is Resource.Error -> {
                        // Handle error state if needed
                        _liked.emit(value = false)
                    }
                    is Resource.Success -> {
                        _likeStates.update { it.toMutableMap().apply { put(requestId ?: -1, true) } }
                        _isLiked.emit(State.success())
                        _liked.emit(value = true)
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
                        _liked.emit(value = true)
                    }
                    is Resource.Success -> {
                        _likeStates.update { it.toMutableMap().apply { put(requestId ?: -1, false) } }
                        _liked.emit(value = false)
                    }
                }
            }
        }
    }


    private val _deleteRequest: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val deleteRequest: StateFlow<State> = _deleteRequest
    fun deleteRequestById(requestId: Int) {
        viewModelScope.launch {
            deleteRequestByIdUseCase.invoke(requestId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state if needed
                        _deleteRequest.emit(State.loading())
                    }
                    is Resource.Error -> {
                        // Handle error state if needed
                        _deleteRequest.emit(State.error(message = resource.message))
                    }
                    is Resource.Success -> {
                        _moviesState.update { pagingData ->
                            pagingData.filter { it.requestId != requestId }
                        }
                        _likeStates.update { it - requestId }
                        _deleteRequest.emit(State.success())
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

//    init {
//        loadComments(202)
//    }
    // Стандартный поток PagingData, который будет содержать комментарии
    private var _comments: Flow<PagingData<CommentData>>? = null
    val comments: Flow<PagingData<CommentData>> get() = _comments!!

    // Функция для загрузки комментариев по идентификатору запроса
    fun loadComments(requestId: Int?) {
        viewModelScope.launch {
            _comments = getCommentUseCase.invoke(requestId)
        }
    }



    private val _commentState: MutableStateFlow<State> = MutableStateFlow(State.loading())
    val commentState: StateFlow<State> = _commentState

    private val _commentResponse = MutableStateFlow<CommentResponse>(CommentResponse())
    val commentResponse: StateFlow<CommentResponse> get() = _commentResponse

    fun sendComment(requestId: Int?, commentText: CommentRequest) {
        viewModelScope.launch {
            sendCommentUseCase(requestId, commentText).collect { response ->
                response.data?.let {
                    _commentResponse.value = it

//                    val currentComments = _comments
//                    val updatedComments = currentComments.insertItemAtStart(it.commentData)
//                    _comments.value = updatedComments
//                    _commentState.value = State.success()

                } ?: run {
                    // Логирование или обработка случая, когда response.data является null
                    Log.e("MyRequestViewModel", "Response data is null")
                }
            }
        }
    }












//
//    private val _requestId = MutableStateFlow<Int?>(null)
//    val requestId: StateFlow<Int?> = _requestId
//
//    val commentss: Flow<PagingData<CommentData>> = _requestId.flatMapLatest { id ->
//        getCommentUseCase(id)
//    }.cachedIn(viewModelScope)
//
//    fun setRequestId(id: Int?) {
//        _requestId.value = id
//    }

//    init {
//        loadComments(202)
//    }


}
//
//@HiltViewModel
//class MyRequestViewModel @Inject constructor(
//    private val myRequestUseCase: MyRequestUseCase,
//    private val likeUseCase: LikeUseCase,
//    private val removeLikeUseCase: RemoveLikeUseCase,
//    private val deleteRequestByIdUseCase: DeleteRequestByIdUseCase,
//    private val getRequestByIdUseCase: GetRequestByIdUseCase
//) : ViewModel() {
//
//    val myRequests = myRequestUseCase().cachedIn(viewModelScope)
//
//    private val _moviesState: MutableStateFlow<PagingData<FilterData>> =
//        MutableStateFlow(value = PagingData.empty())
//    val moviesState: StateFlow<PagingData<FilterData>> get() = _moviesState
//
//    suspend fun getMovies() {
//        myRequestUseCase.invoke()
//            .distinctUntilChanged()
//            .cachedIn(viewModelScope)
//            .collect {
//                _moviesState.value = it
//            }
//    }
//
//    private val _likeStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
//    val likeStates: StateFlow<Map<Int, Boolean>> get() = _likeStates.asStateFlow()
//
//    private val _isLiked: MutableStateFlow<State> = MutableStateFlow(State.loading())
//    val isLiked: StateFlow<State> = _isLiked
//
//    fun sendLike(requestId: Int) {
//        viewModelScope.launch {
//            likeUseCase.invoke(requestId).collect { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        // Handle loading state if needed
//                    }
//                    is Resource.Error -> {
//                        // Handle error state if needed
//                    }
//                    is Resource.Success -> {
//                        _likeStates.update { it.toMutableMap().apply { put(requestId, true) } }
//                        _isLiked.emit(State.success())
//                    }
//                }
//            }
//        }
//    }
//
//    fun removeLike(requestId: Int) {
//        viewModelScope.launch {
//            removeLikeUseCase.invoke(requestId).collect { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        // Handle loading state if needed
//                    }
//                    is Resource.Error -> {
//                        // Handle error state if needed
//                    }
//                    is Resource.Success -> {
//                        _likeStates.update { it.toMutableMap().apply { put(requestId, false) } }
//                    }
//                }
//            }
//        }
//    }
//
//    fun deleteRequestById(requestId: Int) {
//        viewModelScope.launch {
//            deleteRequestByIdUseCase.invoke(requestId).collect { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        // Handle loading state if needed
//                    }
//                    is Resource.Error -> {
//                        // Handle error state if needed
//                    }
//                    is Resource.Success -> {
//                        _moviesState.update { pagingData ->
//                            pagingData.filter { it.requestId != requestId }
//                        }
//                        _likeStates.update { it - requestId }
//                    }
//                }
//            }
//        }
//    }
//
//    private var _requestByIdState: MutableStateFlow<State?> = MutableStateFlow(null)
//    val requestByIdState: StateFlow<State?> = _requestByIdState.asStateFlow()
//
//    val requestById: MutableStateFlow<FilterData?> = MutableStateFlow(null)
//
//    fun getRequestById(requestId: Int) {
//        viewModelScope.launch {
//            getRequestByIdUseCase.invoke(requestId).collect { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        _requestByIdState.emit(State.loading())
//                    }
//                    is Resource.Error -> {
//                        _requestByIdState.emit(State.error(message = resource.message))
//                    }
//                    is Resource.Success -> {
//                        _requestByIdState.emit(State.success())
//                        requestById.value = resource.data?.data
//                    }
//                }
//            }
//        }
//    }
//}
//
