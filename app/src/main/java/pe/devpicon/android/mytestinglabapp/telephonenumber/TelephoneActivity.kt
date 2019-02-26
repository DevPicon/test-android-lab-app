package pe.devpicon.android.mytestinglabapp.telephonenumber

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.TelephonyManager
import pe.devpicon.android.mytestinglabapp.R


class TelephoneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telephone)

        val tMgr = applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    }
}
