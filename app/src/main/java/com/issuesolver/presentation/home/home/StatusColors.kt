package com.issuesolver.presentation.home.home

import androidx.compose.ui.graphics.Color

data class StatusColors(
    val backgroundColor: Color,
    val textColor: Color,
)

val statusColorMap = mapOf(
    "Gözləmədə" to StatusColors(Color(0xFFc1d0fb), Color(0xFF0169FE)),
    "Baxılır" to StatusColors(Color(0xFFfdebde), Color(0xFFE67B2E)),
    "Əsassızdır" to StatusColors(Color(0xFFfdcbd2), Color(0xFFEB2614)),
    "Həlledildi" to StatusColors(Color(0xFFddf1e4), Color(0xFF429A60)),
    "Arxivdədir" to StatusColors(Color(0xFFf0f0f0), Color(0xFF8C8C8C))
)
