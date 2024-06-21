package app.futured.meridian_multiprocess_repro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import app.futured.meridian_multiprocess_repro.ui.screen.LocationLoggerScreen
import app.futured.meridian_multiprocess_repro.ui.theme.MeridianMultiprocessBugTheme

class SecondaryActivity : ComponentActivity(), DefaultLocationUpdateListener {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, SecondaryActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MeridianMultiprocessBugTheme {
                LocationLoggerScreen(
                    modifier = Modifier.fillMaxSize(),
                    title = "SecondaryActivity",
                    onBack = ::finish
                )
            }
        }
    }
}
