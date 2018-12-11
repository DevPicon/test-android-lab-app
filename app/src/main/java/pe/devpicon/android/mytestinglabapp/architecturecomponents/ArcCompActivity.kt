package pe.devpicon.android.mytestinglabapp.architecturecomponents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pe.devpicon.android.mytestinglabapp.R
import pe.devpicon.android.mytestinglabapp.architecturecomponents.ui.arccomp.ArcCompFragment

class ArcCompActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arc_comp_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ArcCompFragment.newInstance())
                    .commitNow()
        }
    }

}
