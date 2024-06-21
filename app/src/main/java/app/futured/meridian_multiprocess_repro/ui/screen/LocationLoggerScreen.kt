package app.futured.meridian_multiprocess_repro.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.futured.meridian_multiprocess_repro.BuildConfig
import app.futured.meridian_multiprocess_repro.DefaultLocationUpdateListener
import app.futured.meridian_multiprocess_repro.ui.theme.MeridianMultiprocessBugTheme
import com.arubanetworks.meridian.editor.EditorKey
import com.arubanetworks.meridian.location.MeridianLocation
import com.arubanetworks.meridian.location.MeridianLocationManager
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationLoggerScreen(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    onNext: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var meridianLocation by remember { mutableStateOf<MeridianLocation?>(null) }
    var isListening by remember { mutableStateOf(false) }

    val locationManager = remember {
        MeridianLocationManager(/* context = */ context,/* appKey = */
            EditorKey.forApp(BuildConfig.MERIDIAN_APP_ID),/* listener = */
            object : DefaultLocationUpdateListener {
                override fun onLocationUpdate(p0: MeridianLocation?) {
                    Timber.d("location update: $p0")
                    meridianLocation = p0
                }
            })
    }
    
    Scaffold(modifier = modifier, topBar = {
        CenterAlignedTopAppBar(title = { Text(text = title) })
    }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Is Listening: $isListening", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(text = "Last location:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            AnimatedContent(targetState = meridianLocation, label = "content_anim") {
                Text(
                    text = meridianLocation.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                locationManager.startListeningForLocation()
                isListening = true
            }) {
                Text(text = "Start listening for location")
            }
            Button(onClick = {
                locationManager.stopListeningForLocation()
                isListening = false
            }) {
                Text(text = "Stop listening for location")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LocationLoggerScreenPreview() {
    MeridianMultiprocessBugTheme {
        LocationLoggerScreen(
            title = "I'm a toolbar", modifier = Modifier.fillMaxSize()
        )
    }
}
