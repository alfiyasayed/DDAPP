package com.example.dell.DDAPP;

/**
 * Created by ALFIYA on 21/02/2018.
 */


import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
public class reminder extends AppCompatActivity {
    String abc;
    int hourOfDay1,minutes1,hourOfDay2,minutes2;

    TextView tv1,tv2,tv3,tv4;
    CheckedTextView tv5;

    Button bt1,bt2,button;
    PendingIntent pendingIntent1,pendingIntent2;
    AlarmManager alarmManager;
    ToggleButton tb1, tb2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        String name[]={"alarm1","alarm2"};
        String numbers[]={"1"};

        CustomAdapter adapter = new CustomAdapter(this, R.id.Linear,R.id.tv1,name,numbers);


        //tp1 = (TimePicker) findViewById(R.id.tp1);
        //tp2 = (TimePicker) findViewById(R.id.tp2);

      try {

            tb1 = (ToggleButton) findViewById(R.id.tb1);
            tb2 = (ToggleButton) findViewById(R.id.tb2);

            bt1 = (Button) findViewById(R.id.button1);
            bt2 = (Button) findViewById(R.id.button2);
          button = (Button) findViewById(R.id.button);

            tv1 = (TextView) findViewById(R.id.tv1);
            tv2 = (TextView) findViewById(R.id.tv2);

            tv3 = (TextView) findViewById(R.id.tv3);
            tv4 = (TextView) findViewById(R.id.tv4);



            hourOfDay1 = Integer.parseInt(tv1.getText().toString());
            minutes1 = Integer.parseInt(tv2.getText().toString());

          hourOfDay2 = Integer.parseInt(tv3.getText().toString());
          minutes2 = Integer.parseInt(tv4.getText().toString());
        }
catch(Exception e)
        {
           // Toast.makeText(reminder.this, "errorrr"+e, Toast.LENGTH_SHORT).show();
        }

  //  btn=(Button)    findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    DialogFragment dFragment = new TimePickerFragment();

                    // Show the time picker dialog fragment
                    dFragment.show(getFragmentManager(),"Time Picker");
                    //Toast.makeText(reminder.this, "inside onclick"+hourOfDay1, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                   // Toast.makeText(reminder.this, "error"+e, Toast.LENGTH_SHORT).show();

                }
            }

        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    DialogFragment dFragments = new TimepickerFragments();

                    // Show the time picker dialog fragment
                    dFragments.show(getFragmentManager(),"Time Picker");
                   // Toast.makeText(reminder.this, "inside onclick"+hourOfDay2, Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                   // Toast.makeText(reminder.this, "error"+e, Toast.LENGTH_SHORT).show();

                }
            }

        });

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        View v1 = null, v2;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time1;
                if (tb1.isChecked()) {
                    Toast.makeText(reminder.this, "Reminder1 set", Toast.LENGTH_SHORT).show();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay1);
                    calendar.set(Calendar.MINUTE, minutes1);
                    Intent intent = new Intent(reminder.this, AlertReciever.class);
                    pendingIntent1 = PendingIntent.getBroadcast(reminder.this, 0, intent, 0);

                    time1 = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                    if (System.currentTimeMillis() > time1) {
                        if (calendar.AM_PM == 0)
                            time1 = time1 + (1000 * 60 * 60 * 12);
                        else
                            time1 = time1 + (1000 * 60 * 60 * 24);
                    }
                    // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, time1, pendingIntent1);
                    // alarmManager.cancel();
                }
                else {
                    alarmManager.cancel(pendingIntent1);
                    //Toast.makeText(reminder.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
                }


                long time2;
                if (tb2.isChecked()) {
                    Toast.makeText(reminder.this, "Reminder2 set", Toast.LENGTH_SHORT).show();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay2);
                    calendar.set(Calendar.MINUTE, minutes2);
                    Intent intent = new Intent(reminder.this, AlertReciever.class);
                    pendingIntent2 = PendingIntent.getBroadcast(reminder.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    time2 = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                    if (System.currentTimeMillis() > time2) {
                        if (calendar.AM_PM == 0)
                            time2 = time2 + (1000 * 60 * 60 * 12);
                        else
                            time2 = time2 + (1000 * 60 * 60 * 24);
                    }
                    // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, time2, pendingIntent2);
                } else {
                    alarmManager.cancel(pendingIntent2);
                    //Toast.makeText(reminder.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}

//final EditText ed= (EditText) findViewById(R.id.ed);


          /* bt.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        long time1;
        if (tb1.isChecked()) {
            Toast.makeText(reminder.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, tp1.getCurrentHour());
            calendar.set(Calendar.MINUTE, tp1.getCurrentMinute());
            Intent intent = new Intent(this, AlrtReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time1 = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time1) {
                if (calendar.AM_PM == 0)
                    time1 = time1 + (1000 * 60 * 60 * 12);
                else
                    time1 = time1 + (1000 * 60 * 60 * 24);
            }
            // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
            alarmManager.set(AlarmManager.RTC_WAKEUP, time1, pendingIntent);
                                }
        else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(reminder.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
            }


        long time2;
        if (tb2.isChecked()) {
            Toast.makeText(reminder.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, tp2.getCurrentHour());
            calendar.set(Calendar.MINUTE, tp2.getCurrentMinute());
            Intent intent = new Intent(this, AlrtReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time2 = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time2) {
                if (calendar.AM_PM == 0)
                    time2 = time2 + (1000 * 60 * 60 * 12);
                else
                    time2 = time2 + (1000 * 60 * 60 * 24);
            }
            // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
            alarmManager.set(AlarmManager.RTC_WAKEUP, time2, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(reminder.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
   );
}

    /* bt = (Button) findViewById(R.id.button3);

           bt.setOnClickListener(new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(reminder.this, "onClick method", Toast.LENGTH_SHORT).show();
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, 00);
                c.set(Calendar.MINUTE, 00);
                c.set(Calendar.SECOND,00);
                //c.set(Calendar.PM,0);
               // int time = Integer.parseInt(ed.getText().toString());
                Intent i = new Intent(reminder.this, AlrtReciever.class);
                PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);

                //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
                Toast.makeText(reminder.this, "before alarm"+c.getTimeInMillis(), Toast.LENGTH_SHORT).show();
               // alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), pi);
                Toast.makeText(reminder.this, "onClick method"+c, Toast.LENGTH_SHORT).show();
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                Toast.makeText(reminder.this, "after alarm"+c.getTimeInMillis(), Toast.LENGTH_SHORT).show();



            }
            catch (Exception e) {
                Toast.makeText(reminder.this, "Error"+e, Toast.LENGTH_SHORT).show();
            }
            }


    });
*/









