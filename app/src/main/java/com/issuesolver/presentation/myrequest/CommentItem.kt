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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R

@Composable
fun CommentItem(commentText: String?,
                createDate: String?,
                fullName: String? ,
                authority: String?){

    Column(modifier = Modifier
        .drawWithContent {
        drawContent()
        val strokeWidth = 0.2.dp.toPx()
        drawLine(
            color = Color(0xFFc8dcfc),
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = Color(0xFFc8dcfc),
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = strokeWidth
        )

    }
        .padding(top=12.dp, bottom = 12.dp,start=20.dp, end = 20.dp)




    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)

            ) {
                authority?.let {

                        Image(
                            painter =
                            if(it=="USER") {
                                painterResource(R.drawable.et_profile_male)
                            }else{ painterResource(R.drawable.government3_icon) },
                            contentDescription = "government3_icon",
                            modifier = Modifier
                                .size(32.dp)
                        )


                }
//                Image(
//                    painter = painterResource(R.drawable.government3_icon),
//                    contentDescription = "government3_icon",
//                    modifier = Modifier
//                        .size(32.dp)
//                )


                fullName?.let {
                    Text(
                        text = it,
                        color = Color(0xFF2981FF),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start=8.dp)

                    )
                }

            }

            createDate?.let {
                Text(
                    text = it,
                    color = Color(0xFF9D9D9D),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(start=7.dp)
                )
            }
        }

        commentText?.let {
            Text(
                text = it,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(top=12.dp)
            )
        }

    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewCommentItem() {
//    CommentItem()
//}