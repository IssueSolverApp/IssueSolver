package com.issuesolver.presentation.profile.profile

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ProfileUpdateManager {
    private val _updateEvents = MutableSharedFlow<String>()
    val updateEvents = _updateEvents.asSharedFlow()

    suspend fun notifyProfileUpdated(fullName: String) {
        _updateEvents.emit(fullName)
    }
}
