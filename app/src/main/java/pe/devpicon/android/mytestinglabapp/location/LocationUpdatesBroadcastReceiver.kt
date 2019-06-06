package pe.devpicon.android.mytestinglabapp.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.LocationResult


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

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            val action = intent.action
            if (ACTION_PROCESS_UPDATES == action) {
                val result = LocationResult.extractResult(intent)
                result?.let {
                    val locations = result.locations
                    val locationResultHelper = LocationResultHelper(context, locations)
                    // Save the location data to SharedPreferences
                    locationResultHelper.saveResults()
                    // Show notification with the location data
                    locationResultHelper.showNotification()
                    Log.i(TAG, LocationResultHelper.getSavedLocationResult(context))
                }
            }
        }
    }

    companion object {
        private val TAG = "LUBroadcastReceiver"
        const val ACTION_PROCESS_UPDATES = "pe.devpicon.android.mytestinglabapp.location.action" + ".PROCESS_UPDATES"
    }
}