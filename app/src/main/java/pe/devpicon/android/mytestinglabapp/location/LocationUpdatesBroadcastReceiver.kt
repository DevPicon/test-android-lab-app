package pe.devpicon.android.mytestinglabapp.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


/**
 * Receiver for handling location updates.
 *
 * For apps targeting API level O
 * [android.app.PendingIntent.getBroadcast] should be used when
 * requesting location updates. Due to limits on background services,
 * [android.app.PendingIntent.getService] should not be used.
 *
 * Note: Apps running on "O" devices (regardless of targetSdkVersion) may receive updates
 * less frequently than the interval specified in the
 * [com.google.android.gms.location.LocationRequest] when the app is no longer in the
 * foreground.
 */
class LocationUpdatesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

    }

    companion object {
        private val TAG = "LUBroadcastReceiver"
        const val ACTION_PROCESS_UPDATES = "pe.devpicon.android.mytestinglabapp.location.action" + ".PROCESS_UPDATES"
    }
}