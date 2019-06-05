package pe.devpicon.android.mytestinglabapp.notifications

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.devpicon.android.mytestinglabapp.Constants
import pe.devpicon.android.mytestinglabapp.R

class NotificationActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_notification -> pushCommonNotification(v)
            R.id.btn_notification_sound_1 -> pushSoundNotification1(v)
            R.id.btn_notification_sound_2 -> pushSoundNotification2(v)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val btn = findViewById<Button>(R.id.btn_notification)
        val btn2 = findViewById<Button>(R.id.btn_notification_sound_1)
        val btn3 = findViewById<Button>(R.id.btn_notification_sound_2)

        btn.setOnClickListener(this@NotificationActivity)
        btn2.setOnClickListener(this@NotificationActivity)
        btn3.setOnClickListener(this@NotificationActivity)
    }

    private fun pushCommonNotification(v: View) {
        val channelId = Constants.ORDERS_NOTIFICATION_CHANNEL_ID
        val intent = Intent(v.context, LoudNotificationService::class.java)
        intent.putExtra(LoudNotificationService.TAG, channelId)
        v.context.startService(intent)
    }

    private fun pushSoundNotification1(v: View) {
        val channelId = Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID
        val intent = Intent(v.context, LoudNotificationService::class.java)
        intent.putExtra(LoudNotificationService.TAG, channelId)
        Toast.makeText(this@NotificationActivity, "cierra la app", Toast.LENGTH_SHORT).show()
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(5000)
            v.context.startService(intent)
        }
    }

    private fun pushSoundNotification2(v: View) {
        val channelId = Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID_2
        val intent = Intent(v.context, LoudNotificationService::class.java)
        intent.putExtra(LoudNotificationService.TAG, channelId)
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(5000)
            v.context.startService(intent)
        }
    }
}
