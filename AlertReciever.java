package com.example.dell.DDAPP;

/**
 * Created by ALFIYA on 21/02/2018.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import static android.support.v4.app.NotificationCompat.Builder;

public class AlertReciever extends BroadcastReceiver {
    Notification noti;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alert Reciever", Toast.LENGTH_SHORT).show();

    /*    MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();*/

        NotificationManager noti= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Builder builder=new Builder(context)
                .setContentTitle("Medicine Reminder")
                .setContentText("Have Your Medicine Dosage")
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp);


        noti.notify(100,builder.build());
    }
}
