package com.example.dell.DDAPP;

/**
 * Created by ALFIYA on 21/02/2018.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

public class Notify extends BroadcastReceiver {
    Notification noti;
    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context,"Alert Reciever", Toast.LENGTH_SHORT).show();

    /*    MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();*/

        NotificationManager noti= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                .setContentTitle("Update Information")
                .setContentText("Enter Glucose ")
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp);
        Intent notificationIntent = new Intent(context, NavigationSettings.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(contentIntent);


        noti.notify(100,builder.build());
    }
}
