package pe.devpicon.android.mytestinglabapp.notifications

import android.app.IntentService
import android.app.Notification
import android.content.ContentResolver
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import pe.devpicon.android.mytestinglabapp.BuildConfig
import pe.devpicon.android.mytestinglabapp.Constants
import pe.devpicon.android.mytestinglabapp.R

class LoudNotificationService : IntentService(TAG) {

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val channelId = intent.getStringExtra(TAG)
            showNotification(channelId)
        }
    }


    private fun showNotification(channelId: String) {
        val notification: Notification? = when (channelId) {
            Constants.ORDERS_NOTIFICATION_CHANNEL_ID -> commonNotification()
            Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID -> newNotification()
            Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID_2 -> formerNotification()
            else -> null
        }

        val notificationManager = NotificationManagerCompat.from(applicationContext)
        notification?.let {
            notificationManager.notify(NOTIFICATION_ID, it)
        }
    }

    private fun commonNotification(): Notification {
        val builder = NotificationCompat.Builder(this, Constants.ORDERS_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Common Notification")
                .setContentText("this is a common notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(false)
                .setAutoCancel(true)

        return builder.build()
    }

    private fun newNotification(): Notification {
        val builder = NotificationCompat.Builder(this, Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("New Notification")
                .setContentText("New configuration for notifications")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(false)
                .setAutoCancel(true)

        val soundUri = if (BuildConfig.DEBUG) {
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned_debug)
        } else {
            Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned)
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setSound(soundUri)
            builder.setVibrate(longArrayOf(1000, 1000, 1000))
        } else {
            builder.setSound(soundUri, AudioManager.STREAM_NOTIFICATION)
        }
        return builder.build()
    }

    private fun formerNotification(): Notification {
        val builder = NotificationCompat.Builder(this, Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID_2)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Old Notification")
                .setContentText("Old implementation for notifications")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(false)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            val soundUri = if (BuildConfig.DEBUG) {
                Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned_debug)
            } else {
                Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned)
            }
            builder.setSound(soundUri)
            builder.setVibrate(longArrayOf(1000, 1000, 1000))
        }

        return builder.build()
    }

    companion object {
        const val TAG = "NotificationService"
        const val NOTIFICATION_ID = 123
    }
}