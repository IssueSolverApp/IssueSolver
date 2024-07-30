//import androidx.compose.animation.core.*
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.issuesolver.common.shimmerEffect
//
//@Composable
//fun CommentItemShimmer() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 12.dp, horizontal = 20.dp)
//            .shimmerEffect()
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Box(
//                modifier = Modifier
//                    .size(32.dp)
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colorScheme.surfaceVariant)
//            )
//            Spacer(Modifier.width(8.dp))
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .height(15.dp)
//                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
//            )
//        }
//        Spacer(Modifier.height(12.dp))
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(14.dp)
//                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
//        )
//    }
//}
