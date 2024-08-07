package com.issuesolver.presentation.home.home

import com.issuesolver.presentation.myrequest.CommentItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue.Hidden
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.R
import com.issuesolver.common.PlaceholderShimmerCard
import com.issuesolver.common.PlaceholderShimmerCard2
import com.issuesolver.common.StatusR
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDragHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.White)
        ,




    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetHome(
    onDismiss: () -> Unit,
    viewModel: TestViewModel,
    id: Int?
) {
    val modalBottomSheetState = rememberModalBottomSheetState(true)
    val coroutineScope = rememberCoroutineScope()
    var textFieldValue by remember { mutableStateOf("") }

    var sendReq by remember { mutableStateOf(false) }

    viewModel.loadComments(id)
    val comments: LazyPagingItems<CommentData> = viewModel.comments.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            modalBottomSheetState.expand()
        }
    }


    LaunchedEffect(sendReq) {
        if (sendReq && id != null && textFieldValue.isNotBlank()) {
            viewModel.sendComment(id, CommentRequest(textFieldValue))
            sendReq = false // Сброс значения после отправки комментария
            textFieldValue = "" // Очистка текстового поля после отправки
            viewModel.loadComments(id) // Перезагрузка комментариев
        }
    }
    val commentState by viewModel.commentState.collectAsState()

    when(commentState?.status){

        StatusR.LOADING -> {


        }

        StatusR.ERROR -> {


        }
        StatusR.SUCCESS -> {

        }
        else-> {

        }


    }


    ModalBottomSheet(
        onDismissRequest = { onDismiss()  },
        sheetState = modalBottomSheetState,
        dragHandle = { CustomDragHandle() },
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp)
            .imePadding()
            .navigationBarsPadding()

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .background(Color.White)
            ) {

                Column {
                    Icon(
                        painter = painterResource(id = R.drawable.tab),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(4.dp)
                            .width(44.dp),
                        tint = Color(0xFFDEDEDE)
                    )

                    Text(
                        text = "Rəylər",
                        color = Color(0xFF333333),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp, bottom = 20.dp)
                    )
                    Divider(
                        thickness = 1.dp,
                        color = Color(0xFFF0F4F9)
                    )


                    if (comments.itemCount == 0 && comments.loadState.refresh is LoadState.NotLoading && comments.loadState.append.endOfPaginationReached) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Hələlik rəy yoxdur",
                                style = TextStyle(
                                    color = Color(0xFF6E6E6E),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W400,)
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            items(comments.itemCount) { index ->
                                comments[index]?.let {
                                    CommentItem(
                                        it.commentText,
                                        it.createDate,
                                        it.fullName,
                                        it.authority
                                    )
                                }
                            }
                            comments.apply {
                                when {
                                    loadState.refresh is LoadState.Loading -> {
                                        items(5) {
                                            PlaceholderShimmerCard2()
                                        }
                                    }

                                    loadState.append is LoadState.Loading -> {
                                        item {
                                        }
                                    }

                                    loadState.refresh is LoadState.Error -> {
                                        val e = comments.loadState.refresh as LoadState.Error
                                        item {
                                            Text(text = "Error: ${e.error.localizedMessage}")
                                        }
                                    }

                                    loadState.append is LoadState.Error -> {
                                        val e = comments.loadState.append as LoadState.Error
                                        item {
                                            Text(text = "Error: ${e.error.localizedMessage}")
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }

            Column (modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.White)
            ){

                Divider(
                    thickness = 0.7.dp,
                    color = Color(0xFF2981FF).copy(alpha = 0.28f)
                )


                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    OutlinedTextField(
                        value = textFieldValue,
                        onValueChange = {textFieldValue=it },
                        placeholder = {
                            Text(
                                "Rəyinizi yazın",
                                color = Color(0xFF2981FF).copy(alpha = 0.65f),
                                fontSize = 15.sp
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        modifier = Modifier
                            .weight(1f)
                            .clip(CircleShape)
                            .border(0.5.dp, Color(0xFF2981FF).copy(alpha = 0.28f), CircleShape),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFdfedff),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            cursorColor = Color(0xFF0269FB)
                        )
                    )

                    Image(
                        painter = painterResource(R.drawable.up_icon),
                        contentDescription = "up_icon",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(start = 10.dp)
                            .clickable {
                                if (id != null && textFieldValue.isNotBlank()) {
                                    sendReq = true // Триггер отправки комментария
                                }
                            }
                    )
                }
            }

        }
    }
}

