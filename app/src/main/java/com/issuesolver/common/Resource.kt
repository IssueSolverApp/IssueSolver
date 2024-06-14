package com.issuesolver.common

import com.issuesolver.domain.entity.networkModel.RegisterErrorResponseModel

sealed class Resource <T>(var data: T? = null, var message: String? = null){

    class Success<T>(data: T) : Resource<T>(data = data)

    class Error<T>(message: RegisterErrorResponseModel) : Resource<T>(message = message.message)

    class Loading<T>() : Resource<T>()

}