import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.dev.taquarylab.busaoshare.components.DynamicContentTheme
import com.dev.taquarylab.busaoshare.components.rememberDynamicColorState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import com.dev.taquarylab.busaoshare.ui.AppTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
internal
fun App() {
    val dynamicColorState = rememberDynamicColorState()
    DynamicContentTheme(dynamicColorState) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.surfaceContainerLowest
        ) {
            NoFeeds { "" }
        }
    }
//    MaterialTheme {
//        var greetingText by remember { mutableStateOf("Hello, World!") }
//        var showImage by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = {
//                greetingText = "Hello, ${getPlatformName()}"
//                showImage = !showImage
//            }) {
//                Text(greetingText)
//            }
//            AnimatedVisibility(showImage) {
//                Image(
//                    painterResource("compose-multiplatform.xml"),
//                    null
//                )
//            }
//        }
//    }
}

expect fun getPlatformName(): String

@Composable
private fun NoFeeds(onNoFeedsSwipeUp: () -> Unit) {
    Column(
        modifier =
        Modifier.fillMaxSize().padding(bottom = 136.dp).pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                if (dragAmount.y < 0) {
                    onNoFeedsSwipeUp()
                }
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No feeds present!",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            color = AppTheme.colorScheme.textEmphasisHigh
        )

        Spacer(Modifier.requiredHeight(8.dp))

        Text(
            text = "Swipe up to get started",
            style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
            color = AppTheme.colorScheme.textEmphasisMed
        )

        Spacer(Modifier.requiredHeight(12.dp))

        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
            tint = AppTheme.colorScheme.tintedForeground
        )
    }
}
