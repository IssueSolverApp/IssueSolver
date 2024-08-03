package com.issuesolver.presentation.bottombar

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter

data class ButtonData(
    val route: String,
    val icon: Painter,
    val text: String,
)