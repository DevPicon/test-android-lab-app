package pe.devpicon.android.mytestinglabapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pe.devpicon.android.mytestinglabapp.charts.ChartActivity;
import pe.devpicon.android.mytestinglabapp.currencies.CurrencyActivity;
import pe.devpicon.android.mytestinglabapp.doubleinstance.InitialActivity;
import pe.devpicon.android.mytestinglabapp.imagespicasso.ImagePicassoActivity;
import pe.devpicon.android.mytestinglabapp.location.LocationActivity;
import pe.devpicon.android.mytestinglabapp.notifications.NotificationActivity;
import pe.devpicon.android.mytestinglabapp.restorestate.RestoreStateActivity;
import pe.devpicon.android.mytestinglabapp.roundedimage.RoundedImageActivity;
import pe.devpicon.android.mytestinglabapp.scanner.ScanProductActivity;
import pe.devpicon.android.mytestinglabapp.zoom.ZoomImageActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toRestoreState(View view) {
        startActivity(new Intent(this, RestoreStateActivity.class));
    }

    public void toPicassoImageLoader(View view) {
        startActivity(new Intent(this, ImagePicassoActivity.class));
    }

    public void toMChartTest(View view) {
        startActivity(new Intent(this, ChartActivity.class));
    }

    public void toZoomPhotoView(View view) {
        startActivity(new Intent(this, ZoomImageActivity.class));
    }

    public void toOpenActivities(View view) {
        startActivity(new Intent(this, InitialActivity.class));
    }

    public void toCurrencies(View view) {
        startActivity(new Intent(this, CurrencyActivity.class));
    }

    public void toRoundedImages(View view) {
        startActivity(new Intent(this, RoundedImageActivity.class));
    }

    public void toCamera(View view) {
        startActivity(new Intent(this, ScanProductActivity.class));
    }

    public void toNotification(View view) {
        startActivity(new Intent(this, NotificationActivity.class));
    }

    public void toLocations(View view) {
        startActivity(new Intent(this, LocationActivity.class));
    }
}
