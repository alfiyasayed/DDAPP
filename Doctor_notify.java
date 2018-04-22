package com.example.dell.DDAPP;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ALFIYA on 03/04/2018.
 */



    public class Doctor_notify extends BroadcastReceiver {
        Notification noti;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference myRef;
    public String UserId,contact;
    int i;

    @Override
        public void onReceive(Context context, Intent intent) {

        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();

       // Toast.makeText(context,"Alert Reciever", Toast.LENGTH_SHORT).show();

    /*    MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();*/

            NotificationManager noti= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                    .setContentTitle("Glucose level is not normal")
                    .setContentText("CALL YOUR DOCTOR")
                    .setAutoCancel(true)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setSmallIcon(R.drawable.ic_alarm_black_24dp);
            Intent notificationIntent = new Intent(context, Doctor_notify.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            DatabaseHelper obj = new DatabaseHelper(context);

            Cursor cursor = obj.searchcontact(UserId);
            i = 0;
            while (cursor.moveToNext()) {
                contact = cursor.getString(i);
                i++;
            }

            builder.setContentIntent(contentIntent);
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(contact));


            noti.notify(100,builder.build());
        }
    }


