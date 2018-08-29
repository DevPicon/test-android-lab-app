package pe.devpicon.android.mytestinglabapp.app;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import pe.devpicon.android.mytestinglabapp.utils.SecurityProviderHelper;

public class LabApplication extends Application {
    private static final String TAG = "LabApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        SecurityProviderHelper.upgradeSecurityProvider(this);

        setupPicasso();
    }

    private void setupPicasso() {

        OkHttpClient.Builder builder = SecurityProviderHelper.enableTls12OnPreLollipop(new OkHttpClient.Builder());

        Picasso build = new Picasso.Builder(this)
                .loggingEnabled(true)
                .downloader(new OkHttp3Downloader(builder.build()))
                .build();

        Picasso.setSingletonInstance(build);
    }
}
