package pe.devpicon.android.mytestinglabapp.animtransitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.devpicon.android.mytestinglabapp.R;

public class AnimChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate_from_left, R.anim.activity_close_translate_to_left);
        setContentView(R.layout.activity_anim_child);
    }
}
