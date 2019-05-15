package pe.devpicon.android.mytestinglabapp.notifications

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC
import pe.devpicon.android.mytestinglabapp.BuildConfig
import pe.devpicon.android.mytestinglabapp.R

class LoudNotificationService : IntentService(TAG) {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Asignación de Pedidos"
            val description = "Notificaciones de la asignación de pedidos"

            val channel = NotificationChannel(CHANNEL_ID_MAIN, "Channel Main", NotificationManager.IMPORTANCE_LOW)
            val sound = NotificationChannel(CHANNEL_ID_SOUND, name, NotificationManager.IMPORTANCE_HIGH)
            sound.description = description
            sound.enableVibration(true)
            sound.vibrationPattern = longArrayOf(0, 300, 0, 400, 0, 500)
            val aa = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setLegacyStreamType(AudioManager.STREAM_NOTIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()

            // Step 8 - Set notification sound and vibrate
            val soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned_debug)
            sound.setSound(soundUri, aa)

            //sound.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), aa)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            notificationManager.createNotificationChannel(sound)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val channelId = intent.getStringExtra(TAG)
            showNotification(channelId)
        }
    }


    private fun showNotification(channelId: String) {
        val inProgress = channelId == CHANNEL_ID_MAIN
        val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Work")
                .setContentText(if (inProgress) "InProgress" else "Finished")
                .setOngoing(inProgress)
                .setVisibility(VISIBILITY_PUBLIC)
                .setAutoCancel(!inProgress)

        if (!inProgress) {
            builder.setCategory(NotificationCompat.CATEGORY_ALARM)
        }

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

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {
        const val TAG = "NotificationService"
        const val CHANNEL_ID_MAIN = "Main"
        const val CHANNEL_ID_SOUND = "Sound"
        const val NOTIFICATION_ID = 123
    }
}