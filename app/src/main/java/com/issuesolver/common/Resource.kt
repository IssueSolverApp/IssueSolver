package com.issuesolver.common

import android.net.http.UrlRequest
import com.issuesolver.domain.entity.networkModel.RegisterErrorResponseModel

sealed class Resource <T>(var data: T? = null, var message: String? = null){

    class Success<T>(data: T) : Resource<T>(data = data)

    class Error<T>(message: String) : Resource<T>(message = message)

    class Loading<T>() : Resource<T>()

}

//
//
//data class LCE<T>(
//    var status: Status? = null,
//    var data: T? = null,
//    var throwable: Throwable? = null,
//    var code: Int? = null,
//    var errorMessage: String? = null
//) {
//
//    companion object {
//
//        fun <T> loading(data: T?=null): LCE<T> {
//            return LCE(
//                status = Status.LOADING,
//                data = data
//            )
//        }
//
//        fun <T> content(data: T?): LCE<T> {
//            return LCE(
//                status = Status.CONTENT,
//                data = data
//            )
//        }
//
//        fun <T> error(throwable: Throwable? = null, code: Int? = null,errorMessage: String? = null): LCE<T> {
//            return LCE(
//                status = Status.ERROR,
//                throwable = throwable,
//                code = code,
//                errorMessage = errorMessage
//            )
//        }
//    }
//}