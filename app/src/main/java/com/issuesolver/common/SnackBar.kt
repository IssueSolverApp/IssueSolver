package com.issuesolver.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.issuesolver.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


class SnackbarManager {
    private val _messages = MutableSharedFlow<String>()
    val messages = _messages.asSharedFlow()

    suspend fun showMessage(message: String) {
        _messages.emit(message)
    }

    companion object {
        val instance = SnackbarManager()
    }
}



@Composable
fun SnackBar(snackbarData: String, snackbarHostState: SnackbarHostState) {
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
@Composable
fun TopSnackbarHost(snackbarHostState: SnackbarHostState) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Ensure it spans the full width
            .padding(top = 16.dp) // Add some padding at the top if needed
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.TopCenter), // Now align works because it's inside a Box
            snackbar = { data ->
                SnackBar(snackbarData = "Sorğunuz uğurla paylaşıldı", snackbarHostState = snackbarHostState)
            }
        )
    }
}



val LocalSnackbarManager = compositionLocalOf<SnackbarManager> { error("No Snackbar Manager found!") }


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