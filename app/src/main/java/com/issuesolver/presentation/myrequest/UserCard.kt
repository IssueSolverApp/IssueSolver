package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R

@Composable
fun UserCard() {
    var expanded by remember { mutableStateOf(false) }
    val fullText =
        "Office ipsum you must be muted. Teeth recap latest didn't at. Innovation hill as wider assassin heads-up stronger give.Innovation hill as wider assassin heads-up stronger give.Innovation hill as wider assassin heads-up stronger give.Innovation hill as wider assassin heads-up stronger give.Innovation hill as wider assassin heads-up stronger give.Innovation hill as wider assassin heads-up stronger give."
    val shortText = fullText.take(100) + "..."

    //Box(modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp, top = 50.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {

        Column(modifier = Modifier.wrapContentHeight()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)

            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.et_profile_male), // Replace with your drawable resource
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Aynur Qəmbərova",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF2981FF),

                        )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(end = 16.dp)
                ) {

                    Button(
                        onClick = { /* Do something */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD8E8FF)),
                        shape = RoundedCornerShape(50.dp),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        Spacer(modifier = Modifier.width(20.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color(0xFF007AFF), CircleShape)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Gözləmədə",
                            color = Color(0xFF0169FE),
                            fontSize = 13.sp,
                            modifier = Modifier.padding(end = 20.dp)
                        )
                    }
                }


            }

            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 8.dp,
                    bottom = 10.dp
                )
            )

            Button(
                onClick = { /* Do something */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0F4F9)),
                shape = RoundedCornerShape(50.dp),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.padding(start = 20.dp)
            ) {
//                    Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = "Küçə heyvanlarına qarşı zorbalıq",
                    color = Color(0xFF8C8C8C),
                    fontSize = 13.sp,

                    )
            }



            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp)) {
                val text = if (expanded) fullText else shortText
                val annotatedString = buildAnnotatedString {
                    append(text)
                    if (!expanded) {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(" daha çox göstər")
                        }
                        addStringAnnotation(
                            tag = "read_more",
                            annotation = "read_more",
                            start = text.length,
                            end = text.length + " daha çox göstər".length
                        )
                    }
                }

                ClickableText(
                    text = annotatedString,
                    style = TextStyle(fontSize = 16.sp),
                    onClick = { offset ->
                        annotatedString.getStringAnnotations("read_more", offset, offset)
                            .firstOrNull()?.let {
                                expanded = true
                            }
                    }
                )
            }

            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 16.dp,
                )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(start = 16.dp, end = 16.dp)
            ) {
                Row {
                    IconButton(onClick = { /* Like action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart_default), // Replace with your icon
                            contentDescription = null
                        )
                    }

                    IconButton(onClick = { /* Like action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.coment), // Replace with your icon
                            contentDescription = null
                        )
                    }
                }
                Row {
                    IconButton(onClick = { /* Like action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_unabale), // Replace with your icon
                            contentDescription = null
                        )
                    }
                }
            }

        }


    }
    //}



}