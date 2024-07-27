package com.issuesolver.common

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.issuesolver.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SnackBar(snackbarData: String) {
    Snackbar(
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth(),
        containerColor = Color(0xFFB9EFCC),
        contentColor = Color(0xFF429A60)
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

@Preview(showBackground = true)
@Composable
fun PreviewSnackBar() {
    SnackBar(snackbarData = "Sorğunuz uğurla paylaşıldı")
}



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