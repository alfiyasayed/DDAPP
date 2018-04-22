package com.example.dell.DDAPP;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Navigation_reminder extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;
    public FirebaseAuth.AuthStateListener mAuthListner;
    public DatabaseReference myRef;
    public String UserId;
    String abc;
    int hourOfDay1,minutes1,hourOfDay2,minutes2;

    TimePicker alarmTimePicker1, alarmTimePicker2;
    PendingIntent pendingIntent1, pendingIntent2;
    AlarmManager alarmManager1,alarmManager2;
    Button button;
    ToggleButton tb1, tb2;
    long time1, time2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        // Toast.makeText(Navigation_WaterIntakeActivity.this, "Hello", Toast.LENGTH_SHORT).show();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();

        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(Navigation_reminder.this ,"Successful Signin with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Navigation_reminder.this ,"Successful Signout",Toast.LENGTH_SHORT).show();
                }
            }

        };
        alarmTimePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        tb1 = (ToggleButton) findViewById(R.id.toggleButton1);
        button = (Button) findViewById(R.id.button4);


        alarmTimePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        tb2 = (ToggleButton) findViewById(R.id.toggleButton2);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void OnToggleClicked(View v1) {


        if (tb1.isChecked()) {
            Toast.makeText(Navigation_reminder.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker1.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker1.getCurrentMinute());
            Intent intent = new Intent(Navigation_reminder.this, AlarmReciever.class);
            pendingIntent1 = PendingIntent.getBroadcast(Navigation_reminder.this, 0, intent, 0);

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
            Toast.makeText(Navigation_reminder.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
        if (tb2.isChecked()) {
            Toast.makeText(Navigation_reminder.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR_OF_DAY, alarmTimePicker2.getCurrentHour());
            calendar1.set(Calendar.MINUTE, alarmTimePicker2.getCurrentMinute());
            Intent intent = new Intent(Navigation_reminder.this, AlarmReciever.class);
            pendingIntent1 = PendingIntent.getBroadcast(Navigation_reminder.this, 1, intent, 0);

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
            Toast.makeText(Navigation_reminder.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_waterintake) {

            Intent i =new Intent(Navigation_reminder.this,Navigation_WaterIntakeActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_settings) {
            Intent i =new Intent(Navigation_reminder.this,NavigationSettings.class);
            startActivity(i);

        }
        else if (id == R.id.nav_alarm) {
            Intent i =new Intent(Navigation_reminder.this,Navigation_reminder.class);
            startActivity(i);

        }else if (id == R.id.nav_step) {
            Intent i =new Intent(Navigation_reminder.this,Navigation_stepCounter.class);
            startActivity(i);


        } else if (id == R.id.nav_tips) {
            Intent i =new Intent(Navigation_reminder.this,Navigation_tips.class);
            startActivity(i);


        }else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Toast.makeText(this,"Successfull Signout",Toast.LENGTH_SHORT).show();
            SaveSharedPreference.clearUserName(Navigation_reminder.this);

            Intent i = new Intent(Navigation_reminder.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);


        } else if (id == R.id.nav_diet) {
            Intent i = new Intent(Navigation_reminder.this,DietActivity.class);
            //  i.putExtra("id",bmr);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
