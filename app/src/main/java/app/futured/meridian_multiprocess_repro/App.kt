package app.futured.meridian_multiprocess_repro

import android.app.Application
import android.util.Log
import com.arubanetworks.meridian.Meridian
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        plantTimber()
        setupMeridian()
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupMeridian() {
        Meridian.configure(this, BuildConfig.MERIDIAN_EDITOR_TOKEN)
        with(Meridian.getShared()) {
            logLevel = Log.WARN
            bluedotComputationInterval = 2 // seconds
            setForceSimulatedLocation(true)
        }
    }
}
