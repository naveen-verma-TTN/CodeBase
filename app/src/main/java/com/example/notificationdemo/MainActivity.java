package com.example.notificationdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private NotificationManager notificationManager;
    private static String CHANNEL_ID = "CHANNEL_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }


                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        Log.e(TAG,"-------------"+ token);

                        // Log and toast
//                        String msg = getString(R.string.tokencode,token);
//                        Log.d(TAG, msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    /**
     * Create Notification channel
     */

    private void CreateNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    "MY_NOTIFICATION_CHANNEL",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getString(R.string.message));

            notificationManager.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder notificationBuilder = getAndroidChannelNotification("Notification 1", "Hello this is my notification");
            if (notificationBuilder != null) {
                notificationManager.notify(100,notificationBuilder.build());
            }
        }

    }

    /**
     * Notification builder
     */

    private NotificationCompat.Builder getAndroidChannelNotification(String title, String body) {
        Intent notificationIntent = new Intent(this, second.class);
        PendingIntent intent = PendingIntent.getActivity(this,100,
                notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    // persistence notification
                    .setOngoing(true)
                    .setSmallIcon(android.R.drawable.stat_sys_warning)
//                    .setContentIntent(intent)
                    .addAction(R.drawable.ic_reply, "This is an action", intent)
                    .addAction(R.drawable.ic_reply, "This is an action", intent);
        }
        return null;
    }

    /**
     * Notification with big image
     */
    public void updateNotification(View view){
        NotificationCompat.Builder notifyBuilder = getAndroidChannelNotification("Notification 1", "Hello this is my notification");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image);
            if (notifyBuilder != null) {
                notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .setBigContentTitle("Notification Updated!"));
            }
        }
        if (notifyBuilder != null) {
            notificationManager.notify(100, notifyBuilder.build());
        }

    }


    public void notification(View view){
        CreateNotificationChannel();
    }

    public  void cancelNotification(View view){
        notificationManager.cancel(100);
    }
}
