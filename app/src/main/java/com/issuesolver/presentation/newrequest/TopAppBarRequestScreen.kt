package com.issuesolver.presentation.newrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R

@Composable
fun Navigation(){


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 20.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(
                text = "Yeni Sorğu",
                fontSize = 28.sp,
                color = Color(0xFF2981FF)
            )

            Image(
                painter = painterResource(id = R.drawable.mingcute_information_line),
                contentDescription = "Example Image",
                modifier = Modifier
                    .size(32.dp)
                //.padding(end = 20.dp, top = 20.dp)
            )
        }
        Text(text = "Xahiş olunur, sorğu üçün məlumatları daxil edin",
            fontSize = 15.sp,
            color = Color(0xFF9D9D9D),
            modifier = Modifier.padding(top = 8.dp))
    }




}