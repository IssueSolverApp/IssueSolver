package com.issuesolver.presentation.myrequest

import androidx.lifecycle.ViewModel
import com.issuesolver.domain.usecase.myrequestusecase.MyRequestUseCase
import javax.inject.Inject

class MyRequestViewModel @Inject constructor(private val getMyRequestUseCase: MyRequestUseCase) :
    ViewModel() {



}