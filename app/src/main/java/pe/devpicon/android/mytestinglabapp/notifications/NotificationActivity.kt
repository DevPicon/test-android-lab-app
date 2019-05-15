package pe.devpicon.android.mytestinglabapp.notifications

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import pe.devpicon.android.mytestinglabapp.R

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val btn = findViewById<Button>(R.id.btn_notification)
        btn.tag = LoudNotificationService.CHANNEL_ID_MAIN
        btn.setOnClickListener { v ->
            val channelId = v.tag as String

            val intent = Intent(v.context, LoudNotificationService::class.java)
            intent.putExtra(LoudNotificationService.TAG, channelId)
            if (channelId == LoudNotificationService.CHANNEL_ID_MAIN) {
                v.tag = LoudNotificationService.CHANNEL_ID_SOUND
            } else {
                v.tag = LoudNotificationService.CHANNEL_ID_MAIN
            }
            v.context.startService(intent)
        }
    }
}
