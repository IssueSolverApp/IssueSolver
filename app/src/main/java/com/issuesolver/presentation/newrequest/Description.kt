package com.issuesolver.presentation.newrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Description(viewModel: RequestScreenViewModel) {

    var isError by remember { mutableStateOf(false) }
    val maxLength = 500

    Column(modifier = Modifier.padding(top = 16.dp)){



        val description by viewModel.description.collectAsState()

        Text(
            text = "Ətraflı izah",
            fontSize = 15.sp,
            color = Color(0xFF000B1B),
        )

        TextField(
            value = description,
            onValueChange = { newText ->
                val textLength = newText.length
                if (textLength <= maxLength) {
                    viewModel.updateDescription(newText)
                    isError = textLength < 10
                }},
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.medium)
                .then(
                if (isError) Modifier.border(
                    1.dp,
                    Color(0xFFEF5648),
                    RoundedCornerShape(12.dp)
                )
                else Modifier.border(
                    1.dp,
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
            ),
            placeholder = {
                Text(
                    "Problem haqqında ətraflı məlumat daxil edin",
                    color = Color(0xFF9D9D9D),
                    fontSize = 15.sp,
                )
            },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Text(
            text = "Min:10-Max:500 simvol",
            fontSize = 15.sp,
            color = Color(0xFF9D9D9D),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}