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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.R
import com.issuesolver.common.AlertDialogExample
import com.issuesolver.common.StatusR
import com.issuesolver.common.PopUp
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import com.issuesolver.presentation.home.home.StatusColors
import com.issuesolver.presentation.home.home.statusColorMap
import kotlinx.coroutines.flow.Flow


@Composable
fun UserCard(
    fullName: String?,
    status: String?,
    description: String?,
    categoryName: String?,
    viewModel: MyRequestViewModel,
    requestId: Int?,
    likeSuccess: Boolean?,
    onClick: () -> Unit,
    comments: LazyPagingItems<CommentData>
) {
    var showDialog by remember { mutableStateOf(false) }

    //viewModel.loadComments(requestId = requestId)
    //val comments: LazyPagingItems<CommentData> = viewModel.commentss.collectAsLazyPagingItems()

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet ({
            showSheet = false
        },
            comments,
//            viewModel,
//            requestId
        )
    }


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

    val statusColors = statusColorMap[status] ?: StatusColors(androidx.compose.ui.graphics.Color.Transparent,
        androidx.compose.ui.graphics.Color.Transparent)

    val likeStates by viewModel.likeStates.collectAsState()
    var favoriteState = likeStates[requestId] ?: likeSuccess
//    if (favoriteState!=likeSuccess){
//        favoriteState=likeSuccess
//    }

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



    if (showDialog) {
        if(status != "Gözləmədə") {

            AlertDialogExample(
                message = "Sorğular yalnız \"Gözləmədə\" statusunda silinə bilər.",
                onConfirmation = { showDialog = false }
            )
        } else
            PopUp(
                text = "Sorğunuzu silməyə əminsiniz?",
                confirm = "Bəli",
                dismiss = "Xeyr",
                onConfirmation = {
                    requestId?.let { viewModel.deleteRequestById(it) }
                    showDialog = false
                },
                onDismiss = { showDialog = false }
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
                            modifier = Modifier.padding(start=8.dp,end=20.dp)
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

            categoryName?.let {
                Text(
                    text = it,
                    color = Color(0xFF8C8C8C),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50.dp))
                        .background(color = Color(0xFFF0F4F9))
                        .padding(top=8.dp, bottom = 8.dp, start = 11.dp, end = 11.dp)
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
                        val icon = if (favoriteState!!) R.drawable.heart_clicked else R.drawable.heart_default
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            tint = if (favoriteState!!) Color.Red else Color(0xFF002252)
                        )
                    }

                    IconButton(onClick = {
                        showSheet = true
                        //viewModel.loadComments(202)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.coment),
                            contentDescription = null
                        )
                    }
                }
                Row {
                    IconButton(onClick = {
                        showDialog = true

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


//
//@Composable
//fun UserCard(
//    fullName: String?,
//    status: String?,
//    description: String?,
//    categoryName: String?,
//    viewModel: MyRequestViewModel,
//    requestId: Int?,
//    likeSuccess: Boolean?,
//    onClick: () -> Unit
//) {
//    var expanded by remember { mutableStateOf(false) }
//    val fullText = description
//    val additionalText = "daha çox göstər..."
//    val approximateCharacterPerLine = 50
//    val maxLines = 3
//
//    val maxTextLength = (approximateCharacterPerLine * maxLines) - additionalText.length
//    val shortText = if (fullText!!.length > maxTextLength) {
//        fullText.take(maxTextLength) + "..."
//    } else {
//        fullText
//    }
//    val likeStates by viewModel.likeStates.collectAsState()
//    var favoriteState = likeStates[requestId] ?: likeSuccess
//    val isLike by viewModel.isLiked.collectAsState()
//    var isLiked by rememberSaveable { mutableStateOf(likeSuccess) }
//
//    when (isLike.status) {
//        StatusR.LOADING -> {
//            // Handle loading state
//        }
//        StatusR.SUCCESS -> {
//            isLiked = true
//        }
//        StatusR.ERROR -> {
//            // Handle error state
//        }
//        else -> {
//            // Handle other states
//        }
//    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .clickable(onClick = onClick),
//        colors = CardDefaults.cardColors(
//            containerColor = androidx.compose.ui.graphics.Color.White
//        )
//    ) {
//        Column(modifier = Modifier
//            .wrapContentHeight()
//            .padding(16.dp)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Image(
//                        painter = painterResource(id = R.drawable.et_profile_male),
//                        contentDescription = "Profile Image",
//                        modifier = Modifier.size(32.dp)
//                    )
//                    Text(
//                        text = fullName ?: "",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = Color(0xFF2981FF),
//                        modifier = Modifier.padding(start = 6.dp),
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.W400
//                    )
//                }
//
//                Row(
//                    modifier = Modifier
//                        .clip(shape = CircleShape)
//                        .background(color = Color(0xFFF5F5F5))
//                        .padding(8.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    IconButton(onClick = {
//                        requestId?.let { id ->
//                            if (isLiked == true) {
//                                viewModel.removeLike(id)
//                            } else {
//                                viewModel.sendLike(id)
//                            }
//                        }
//                        isLiked = isLiked != true
//                    }) {
//                        val icon = if (isLiked == true) R.drawable.heart_clicked else R.drawable.heart_default
//                        Icon(
//                            painter = painterResource(id = icon),
//                            contentDescription = null,
//                            Modifier.size(20.dp),
//                            tint = androidx.compose.ui.graphics.Color.Red
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(
//                    text = status ?: "",
//                    color = Color(0xFF424242),
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontSize = 13.sp
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = categoryName ?: "",
//                    color = Color(0xFF6D6D6D),
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontSize = 13.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Column {
//                val displayText = if (expanded) {
//                    fullText
//                } else {
//                    shortText
//                }
//                Text(
//                    text = buildAnnotatedString {
//                        append(displayText)
//                        if (fullText.length > maxTextLength) {
//                            if (expanded) {
//                                append(" ")
//                                withStyle(
//                                    style = SpanStyle(
//                                        color = Color(0xFF6D6D6D),
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                ) {
//                                    append(additionalText)
//                                }
//                            }
//                        }
//                    },
//                    color = Color(0xFF6D6D6D),
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontSize = 13.sp,
//                    modifier = Modifier.clickable {
//                        expanded = !expanded
//                    }
//                )
//            }
//        }
//    }
//}
