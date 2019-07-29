package com.app.roshni;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {

        SharePreferenceUtils.getInstance().saveString("token" , s);

        Log.d("toekn" , s);



        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("asdasd" , remoteMessage.getData().toString());

        Intent registrationComplete = new Intent("count");

        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);


        JSONObject object = null;
        object = new JSONObject(remoteMessage.getData());

        try {
            handleNotification(object.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onMessageReceived(remoteMessage);
    }

    private void handleNotification(String message) {

        Log.d("notificationData", message);
        String idChannel = "roshni messages";
        Intent mainIntent;

        mainIntent = new Intent(Bean.getContext(), Notifications.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(Bean.getContext(), 0, mainIntent, 0);

        NotificationManager mNotificationManager = (NotificationManager) Bean.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel mChannel = null;
        // The id of the channel.

        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Bean.getContext(), idChannel);
        builder.setContentTitle(Bean.getContext().getString(R.string.app_name))
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentText(message);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(idChannel, Bean.getContext().getString(R.string.app_name), importance);
            // Configure the notification channel.
            mChannel.setDescription(Bean.getContext().getString(R.string.alarm_notification));
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
            }
        } else {
            builder.setContentTitle(Bean.getContext().getString(R.string.app_name))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setColor(ContextCompat.getColor(Bean.getContext(), R.color.colorAccent))
                    .setVibrate(new long[]{100, 250})
                    .setLights(Color.YELLOW, 500, 5000)
                    .setAutoCancel(true);
        }
        if (mNotificationManager != null) {
            mNotificationManager.notify(1, builder.build());
        }


    }

}
