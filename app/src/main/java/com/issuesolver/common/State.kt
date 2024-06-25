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