package pe.devpicon.android.mytestinglabapp.zoom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pe.devpicon.android.mytestinglabapp.R;
import pe.devpicon.android.mytestinglabapp.zoom.view.ZoomImageView;

public class ZoomImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        ZoomImageView photoView = (ZoomImageView) findViewById(R.id.image_consolidation_bill);
        photoView.setImageResource(R.drawable.sample);
    }
}
