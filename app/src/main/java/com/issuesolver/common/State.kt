package com.issuesolver.common

class State(var status: StatusR, var message: String? = null) {

    companion object {
        fun success(): State = State(status = StatusR.SUCCESS)

        fun error(message: String?): State = State(status = StatusR.ERROR, message = message)

        fun loading(): State = State(status = StatusR.LOADING)

    }

}

enum class StatusR {
    SUCCESS, ERROR, LOADING
}