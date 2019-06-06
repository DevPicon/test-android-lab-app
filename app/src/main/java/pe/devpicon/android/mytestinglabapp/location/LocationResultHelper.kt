package pe.devpicon.android.mytestinglabapp.location


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.Location
import android.preference.PreferenceManager
import android.support.v4.app.TaskStackBuilder
import pe.devpicon.android.mytestinglabapp.MainActivity
import pe.devpicon.android.mytestinglabapp.R
import java.text.DateFormat
import java.util.*


/**
 * Class to process location results.
 */
class LocationResultHelper(val mContext: Context, val mLocations: MutableList<Location>) {
    private val PRIMARY_CHANNEL = "default"
    private var mNotificationManager: NotificationManager? = null

    /**
     * Returns the title for reporting about a list of [Location] objects.
     */
    private fun getLocationResultTitle(): String {
        val numLocationsReported = mContext.resources.getQuantityString(
                R.plurals.num_locations_reported, mLocations.size, mLocations.size)
        return numLocationsReported + ": " + DateFormat.getDateTimeInstance().format(Date())
    }

    private fun getLocationResultText(): String {
        if (mLocations.isEmpty()) {
            return mContext.getString(R.string.unknown_location)
        }
        val sb = StringBuilder()
        for (location in mLocations) {
            sb.append("(")
            sb.append(location.latitude)
            sb.append(", ")
            sb.append(location.longitude)
            sb.append(")")
            sb.append("\n")
        }
        return sb.toString()
    }

    /**
     * Saves location result as a string to [android.content.SharedPreferences].
     */
    fun saveResults() {
        PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .putString(KEY_LOCATION_UPDATES_RESULT, getLocationResultTitle() + "\n" +
                        getLocationResultText())
                .apply()
    }

    companion object {
        val KEY_LOCATION_UPDATES_RESULT = "location-update-result"

        /**
         * Fetches location results from [android.content.SharedPreferences].
         */
        fun getSavedLocationResult(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context)
                    .getString(KEY_LOCATION_UPDATES_RESULT, "")
        }

    }

    /**
     * Get the notification mNotificationManager.
     *
     *
     * Utility method as this helper works with it a lot.
     *
     * @return The system service NotificationManager
     */
    private fun getNotificationManager(): NotificationManager {
        if (mNotificationManager == null) {
            mNotificationManager = mContext.getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return mNotificationManager as NotificationManager
    }

    /**
     * Displays a notification with the location results.
     */
    fun showNotification() {
        val notificationIntent = Intent(mContext, MainActivity::class.java)

        // Construct a task stack.
        val stackBuilder = TaskStackBuilder.create(mContext)

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MainActivity::class.java)

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent)

        // Get a PendingIntent containing the entire back stack.
        val notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = Notification.Builder(mContext)
                .setContentTitle(getLocationResultTitle())
                .setContentText(getLocationResultText())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(notificationPendingIntent)

        getNotificationManager().notify(0, notificationBuilder.build())
    }

}