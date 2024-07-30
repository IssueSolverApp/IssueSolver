package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.domain.entity.networkModel.myrequestmodel.CommentData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDragHandle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp) // Высота DragHandle
            .background(Color.Blue) // Ваш цвет


    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    //viewModel: MyRequestViewModel,
    //requestId: Int?,
    comments: LazyPagingItems<CommentData>
    //comments: LazyPagingItems<CommentData>
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var textFieldValue by remember { mutableStateOf("") }

    val viewModel: MyRequestViewModel = hiltViewModel()

    //viewModel.loadComments(requestId = requestId)
//    val comments: LazyPagingItems<CommentData> = viewModel.comments.collectAsLazyPagingItems()

//    LaunchedEffect(requestId) {
//        viewModel.loadComments(requestId)
//    }
//    LaunchedEffect(requestId) {
//        viewModel.setRequestId(requestId) // Замените requestId на нужное значение
//    }

   // viewModel.loadComments(requestId)
    //val comments: LazyPagingItems<CommentData> = viewModel.comments.collectAsLazyPagingItems()
    //val comments = viewModel.commentss.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            modalBottomSheetState.show()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.ScrimColor.red },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tab), // Замените на вашу иконку
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp) // Отступ сверху
                    .height(4.dp)
                    .width(44.dp),
                tint = Color(0xFFDEDEDE)// Размер иконки
            )

            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
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
                }

                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    )  {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        placeholder = {
                            Text(
                                "Rəyinizi yazın",
                                color =Color(0xFF0269FB).copy(alpha = 0.65f),
                                fontSize = 15.sp,
                            )

                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp)
                            .border(
                                0.5.dp,
                                Color(0xFF0269FB).copy(alpha = 0.28f),
                                CircleShape
                            )
                            .clip(CircleShape)
                            .weight(1f)
                        ,
                        colors = TextFieldDefaults.colors(
                            errorContainerColor = Color(0xFFdfedff),
                            focusedContainerColor = Color(0xFFdfedff),
                            disabledContainerColor = Color(0xFFdfedff),
                            unfocusedContainerColor = Color(0xFFcdfedff),
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor=Color.Transparent,
                            unfocusedIndicatorColor =Color.Transparent,
                            cursorColor = Color(0xFF0269FB),

                            )
                    )

                        Image(
                            painter = painterResource(R.drawable.up_icon),
                            contentDescription = "up_icon",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 10.dp)
                        )
                    }

                }



            }

        }

    }


//
//@Preview(showBackground = true)
//@Composable
//fun PreviewBottomSheet() {
//    BottomSheet(onDismiss = {})
//}

