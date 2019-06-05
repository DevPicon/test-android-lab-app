package pe.devpicon.android.mytestinglabapp.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import pe.devpicon.android.mytestinglabapp.BuildConfig;
import pe.devpicon.android.mytestinglabapp.Constants;
import pe.devpicon.android.mytestinglabapp.R;
import pe.devpicon.android.mytestinglabapp.utils.SecurityProviderHelper;

import static android.media.AudioManager.STREAM_NOTIFICATION;

public class LabApplication extends Application {

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

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            assert notificationManager != null;

            {
                CharSequence name = "Pedidos";
                String description = "Notificación de Pedidos";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel orderChannel = new NotificationChannel(
                        Constants.ORDERS_NOTIFICATION_CHANNEL_ID, name, importance);
                orderChannel.setDescription(description);
                notificationManager.createNotificationChannel(orderChannel);
            }

            {
                CharSequence name = "New Config Asignacion de Pedidos";
                String description = "Nuevas notificaciones de la asignación de pedidos";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel assignedOrderChannel = new NotificationChannel(
                        Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID, name, importance);
                assignedOrderChannel.setDescription(description);

                AudioAttributes attributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setLegacyStreamType(STREAM_NOTIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                        .build();

                Uri soundUri;
                soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned);


                assignedOrderChannel.setSound(soundUri, attributes);
                assignedOrderChannel.setVibrationPattern(new long[]{1000, 1000, 1000});
                notificationManager.createNotificationChannel(assignedOrderChannel);
            }

            {
                CharSequence name = "Old Asignacion de Pedidos";
                String description = "Antiguas notificaciones de la asignación de pedido";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel assignedOrderChannel_2 = new NotificationChannel(
                        Constants.ORDERS_ASSIGNMENTS_NOTIFICATION_CHANNEL_ID_2, name, importance);
                assignedOrderChannel_2.setDescription(description);

                AudioAttributes attributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .build();

                Uri soundUri;
                soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + BuildConfig.APPLICATION_ID + "/" + R.raw.order_assigned);


                assignedOrderChannel_2.setSound(soundUri, attributes);
                assignedOrderChannel_2.setVibrationPattern(new long[]{1000, 1000, 1000});
                notificationManager.createNotificationChannel(assignedOrderChannel_2);
            }
        }
    }
}
