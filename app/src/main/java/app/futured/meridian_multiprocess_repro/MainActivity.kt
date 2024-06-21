package app.futured.meridian_multiprocess_repro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import app.futured.meridian_multiprocess_repro.ui.screen.LocationLoggerScreen
import app.futured.meridian_multiprocess_repro.ui.theme.MeridianMultiprocessBugTheme

class MainActivity : ComponentActivity(), DefaultLocationUpdateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MeridianMultiprocessBugTheme {
                LocationLoggerScreen(
                    modifier = Modifier.fillMaxSize(),
                    title = "Default process",
                    onNext = { startActivity(SecondaryActivity.getStartIntent(this)) }
                )
            }
        }
    }
}
