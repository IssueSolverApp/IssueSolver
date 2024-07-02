package com.issuesolver.presentation.common

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateCircularIndicator() {
    var loading by remember { mutableStateOf(false) }


    if (!loading) return

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = Color.Gray,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}