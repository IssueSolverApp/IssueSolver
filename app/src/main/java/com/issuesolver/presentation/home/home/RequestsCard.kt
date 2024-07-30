package com.issuesolver.presentation.home.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.common.AlertDialogExample
import com.issuesolver.common.AlertDialogExample2
import com.issuesolver.common.PopUp
import com.issuesolver.common.StatusR
import com.issuesolver.presentation.myrequest.MyRequestViewModel
import com.issuesolver.presentation.navigation.DetailsScreen
import kotlin.math.roundToInt

@Composable
fun RequestsCard(
    fullName: String?,
    status: String?,
    description: String?,
    categoryName:String?,
    viewModel: TestViewModel,
    requestId: Int?,
    likeSuccess: Boolean?,
    onClick: () -> Unit,
    ) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {

//        AlertDialogExample2(
//            message = "Sorğular yalnız \"Gözləmədə\" statusunda silinə bilər.",
//            onConfirmation = { showDialog = false }
//        )

        PopUp(
            text = "Sorğunuzu ləğv etməyə əminsiniz?",
            confirm = "Bəli",
            dismiss = "Xeyr",
            onConfirmation = {
                // deletee
            },
            onDismiss = { showDialog = false }
        )
    }


    var expanded by remember { mutableStateOf(false) }
    val fullText = description
    val fullName = fullName

    val additionalText = "daha çox göstər..."
    val approximateCharacterPerLine = 50
    val maxLines = 3

    val maxTextLength = (approximateCharacterPerLine * maxLines) - additionalText.length
    val shortText = if (fullText!!.length > maxTextLength) {
        fullText.take(maxTextLength) + "..."
    } else {
        fullText
    }

    val statusColors = statusColorMap[status] ?: StatusColors(
        androidx.compose.ui.graphics.Color.Transparent,
        androidx.compose.ui.graphics.Color.Transparent
    )


    val likeStates by viewModel.likeStates.collectAsState()
    var favoriteState = likeStates[requestId] ?: likeSuccess


    val isLike by viewModel.isLiked.collectAsState()
    var isLiked by rememberSaveable { mutableStateOf(likeSuccess) }


    when (isLike.status) {
        StatusR.LOADING -> {

        }

        StatusR.SUCCESS -> {


        }

        StatusR.ERROR -> {

        }

        else -> {

        }
    }

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheetHome ({
            showSheet = false
        },
            //comments,
            viewModel,
            requestId
        )
    }




    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.ui.graphics.Color.White
        )
    ) {

        Column(
            modifier = Modifier
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
                        modifier = Modifier
                            .size(32.dp)
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
                        .background(color = statusColors.backgroundColor)
                        .padding(8.dp)
                        .clickable(onClick = { }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ellipse_9),
                        contentDescription = "ellipse_9",
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(8.dp),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(statusColors.textColor)


                    )
                    status?.let {
                        Text(
                            text = it,
                            color = statusColors.textColor,
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
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 16.dp
            )
        )

//                    Spacer(modifier = Modifier.width(5.dp))

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
                        ),
                    ) {
                        append("  daha çox göstər")
                    }
                }
            }

            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    if (annotatedString.getStringAnnotations(
                            tag = "read_more",
                            start = offset,
                            end = offset
                        ).isNotEmpty()
                    ) {
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
            modifier = Modifier.padding(
                top = 16.dp,
            )
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                IconButton(onClick = {
//                        if(favoriteState!=likeSuccess){
//
//                        }
                    if (favoriteState!!) {
                        viewModel.removeLike(requestId = requestId)
//                            favoriteState=false

                    } else {
                        viewModel.sendLike(requestId = requestId)
//                            favoriteState=true
                    }
                    favoriteState = !favoriteState!!

                }) {
                    val icon =
                        if (favoriteState!!) R.drawable.heart_clicked else R.drawable.heart_default
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = if (favoriteState!!) androidx.compose.ui.graphics.Color.Red else Color(
                            0xFF002252
                        )
                    )
                }

                IconButton(onClick = {
                    showSheet = true

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.coment),
                        contentDescription = null
                    )
                }
            }
        }

    }
    }
}


    //}




//@Preview(showBackground = true, backgroundColor = 0xF0F4F9)
//@Composable
//fun PreviewUserCard() {
//    UserCard()
//}