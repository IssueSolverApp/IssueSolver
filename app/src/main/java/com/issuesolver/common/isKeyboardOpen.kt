package com.issuesolver.common

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.ViewCompat
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity

@Composable
fun isKeyboardOpen(): Boolean {
    val view = LocalView.current
    val isKeyboardOpen = remember { mutableStateOf(false) }

    // Check keyboard state on each recomposition
    SideEffect {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val insets = ViewCompat.getRootWindowInsets(view)
            isKeyboardOpen.value = insets?.isVisible(WindowInsetsCompat.Type.ime()) ?: false
        }
    }

    return isKeyboardOpen.value
}
