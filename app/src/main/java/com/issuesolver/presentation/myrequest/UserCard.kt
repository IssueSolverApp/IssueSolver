package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R
import com.issuesolver.common.StatusR
import com.issuesolver.presentation.common.LoadingOverlay
import com.issuesolver.presentation.navigation.AuthScreen
import com.issuesolver.presentation.navigation.Graph

@Composable
fun UserCard(
    fullName: String?,
    status: String?,
    description: String?,
    categoryName: String?,
    viewModel: MyRequestViewModel,
    requestId: Int?,
    likeSuccess: Boolean?
) {
    var expanded by remember { mutableStateOf(false) }
    val fullText = description
    val additionalText = "daha çox göstər..."
    val approximateCharacterPerLine = 50
    val maxLines = 3

    val maxTextLength = (approximateCharacterPerLine * maxLines) - additionalText.length
    val shortText = if (fullText!!.length > maxTextLength) {
        fullText.take(maxTextLength) + "..."
    } else {
        fullText
    }
    val likeStates by viewModel.likeStates.collectAsState()
    val favoriteState = likeStates[requestId] ?: likeSuccess ?: false

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.White
        )
    ) {
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.et_profile_male),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = fullName ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF2981FF),
                        modifier = Modifier.padding(start = 6.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W400
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(color = Color(0xFFc8dcfc))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ellipse_9),
                        contentDescription = "ellipse_9",
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(8.dp)
                    )
                    status?.let {
                        Text(
                            text = it,
                            color = Color(0xFF0169FE),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.W400,
                            modifier = Modifier.padding(start = 8.dp, end = 20.dp)
                        )
                    }
                }
            }

            Divider(
                thickness = 0.5.dp,
                color = Color(0xFFc3dcff),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            categoryName?.let {
                Text(
                    text = it,
                    color = Color(0xFF8C8C8C),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50.dp))
                        .background(color = Color(0xFFF0F4F9))
                        .padding(top = 8.dp, bottom = 8.dp, start = 11.dp, end = 11.dp)
                )
            }

            Column(modifier = Modifier.padding(top = 8.dp)) {
                val textToShow = if (expanded) fullText else shortText
                val annotatedString = buildAnnotatedString {
                    append(textToShow)
                    if (!expanded && fullText.length > maxTextLength) {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF2981FF),
                                fontWeight = FontWeight.W500
                            )
                        ) {
                            append("  daha çox göstər")
                        }
                    }
                }

                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        if (annotatedString.getStringAnnotations(tag = "read_more", start = offset, end = offset).isNotEmpty()) {
                            expanded = !expanded
                        }
                    },
                    style = TextStyle(
                        color = Color(0xFF6E6E6E),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Divider(
                thickness = 0.5.dp,
                color = Color(0xFFc3dcff),
                modifier = Modifier.padding(top = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    IconButton(onClick = {
                        if (favoriteState) {
                            viewModel.removeLike(requestId = requestId)
                        } else {
                            viewModel.sendLike(requestId = requestId)
                        }
                    }) {
                        val icon = if (favoriteState) R.drawable.heart_clicked else R.drawable.heart_default
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = androidx.compose.ui.graphics.Color.Red
                        )
                    }

                    IconButton(onClick = { /* Comment action */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.coment),
                            contentDescription = null
                        )
                    }
                }
                Row {
                    IconButton(onClick = {
                        requestId?.let { viewModel.deleteRequestById(it) }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
//@Preview(showBackground = true, backgroundColor = 0xF0F4F9)
//@Composable
//fun PreviewUserCard() {
//    UserCard()
//}