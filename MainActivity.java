package com.example.dell.DDAPP;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

       TimePicker alarmTimePicker1, alarmTimePicker2;
    PendingIntent pendingIntent1, pendingIntent2;
    AlarmManager alarmManager1,alarmManager2;
    Button button;
    ToggleButton tb1, tb2;
    long time1, time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        tb1 = (ToggleButton) findViewById(R.id.toggleButton1);
        button = (Button) findViewById(R.id.button4);


        alarmTimePicker2 = (TimePicker) findViewById(R.id.timePicker2);
         tb2 = (ToggleButton) findViewById(R.id.toggleButton2);

    }

    public void OnToggleClicked(View v1) {


        if (tb1.isChecked()) {
            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker1.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker1.getCurrentMinute());
            Intent intent = new Intent(MainActivity.this, AlarmReciever.class);
            pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            time1 = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time1) {
                if (calendar.AM_PM == 0)
                    time1 = time1 + (1000 * 60 * 60 * 12);
                else
                    time1 = time1 + (1000 * 60 * 60 * 24);
            }
            alarmManager1.set(AlarmManager.RTC_WAKEUP, time1, pendingIntent1);
        }
        else {
            alarmManager1.cancel(pendingIntent1);
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
        if (tb2.isChecked()) {
            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR_OF_DAY, alarmTimePicker2.getCurrentHour());
            calendar1.set(Calendar.MINUTE, alarmTimePicker2.getCurrentMinute());
            Intent intent = new Intent(MainActivity.this, AlarmReciever.class);
            pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent, 0);

            time2 = (calendar1.getTimeInMillis() - (calendar1.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time2) {
                if (calendar1.AM_PM == 0)
                    time2 = time2 + (1000 * 60 * 60 * 12);
                else
                    time2 = time2 + (1000 * 60 * 60 * 24);


            }
            alarmManager1.set(AlarmManager.RTC_WAKEUP, time2, pendingIntent1);
        }
        else{
            alarmManager1.cancel(pendingIntent1);
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }


    }
}

