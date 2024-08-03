package com.issuesolver.common

class State<T>(var status: StatusR, var message: String? = null) {

    companion object {
        fun success(): State<Any?> = State(status = StatusR.SUCCESS)

        fun error(message: String?): State<Any?> = State(status = StatusR.ERROR, message = message)

        fun loading(): State<Any?> = State(status = StatusR.LOADING)

    }

}

enum class StatusR {
    SUCCESS, ERROR, LOADING
}





class StateSignIn(var status: StatusSignIn, var message: String? = null) {

    companion object {
        fun success(): StateSignIn = StateSignIn(status = StatusSignIn.SUCCESS)

        fun error(message: String?): StateSignIn = StateSignIn(status = StatusSignIn.ERROR, message = message)

        fun loading(): StateSignIn = StateSignIn(status = StatusSignIn.LOADING)

        fun conflict(message: String?):StateSignIn = StateSignIn(status = StatusSignIn.CONFLICT, message = message)

    }

}

enum class StatusSignIn {
    SUCCESS, ERROR, LOADING, CONFLICT
}