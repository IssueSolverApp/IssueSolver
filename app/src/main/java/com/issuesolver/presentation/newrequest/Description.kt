package com.issuesolver.presentation.newrequest

import androidx.compose.foundation.background
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
fun Description() {

    Column(modifier = Modifier.padding(top = 16.dp)){

        var selectedText by remember { mutableStateOf("") }

        Text(
            text = "Ətraflı izah",
            fontSize = 15.sp,
            color = Color(0xFF000B1B),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

        TextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                .clip(MaterialTheme.shapes.medium),
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
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp)
        )
    }
}