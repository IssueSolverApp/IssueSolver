package com.issuesolver.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.issuesolver.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@Composable
fun SnackBar(snackbarData: String, snackbarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = snackbarData) {
        snackbarHostState.showSnackbar(
            message = snackbarData,
            duration = SnackbarDuration.Short
        )
    }

    Snackbar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFFB9EFCC),
        contentColor = Color(0xFF429A60),

        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.success),
                contentDescription = "success",
                modifier = Modifier.padding(end = 25.dp)
            )
            Text(text = snackbarData)
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSnackBar() {
//    SnackBar(snackbarData = "Sorğunuz uğurla paylaşıldı")
//}



//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun SnackbarExample() {
//    val snackbarHostState = remember { SnackbarHostState() }
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(Unit) {
//        scope.launch {
//            snackbarHostState.showSnackbar(
//                message = "Your request was successfully shared",
//            )
//            delay(3000)
//            snackbarHostState.currentSnackbarData?.dismiss()
//        }
//    }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
//    ) {
//        Box(modifier = Modifier.fillMaxSize()){
//            Text(text = "fdsad")
//        }
//    }
//}