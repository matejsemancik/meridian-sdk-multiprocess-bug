package app.futured.meridian_multiprocess_repro

import com.arubanetworks.meridian.location.MeridianLocation
import com.arubanetworks.meridian.location.MeridianLocationManager.LocationUpdateListener

interface DefaultLocationUpdateListener : LocationUpdateListener {
    override fun onEnableBluetoothRequest() = Unit

    override fun onEnableGPSRequest() = Unit

    override fun onLocationError(p0: Throwable?) = Unit

    override fun onLocationUpdate(p0: MeridianLocation?) = Unit

    override fun onEnableWiFiRequest() = Unit
}
