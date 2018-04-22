package com.example.dell.DDAPP;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Navigation_stepCounter extends AppCompatActivity
        implements SensorEventListener,NavigationView.OnNavigationItemSelectedListener {
    TextView tv_steps;

    SensorManager sensormanager;
    boolean running=false;


    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;
    public FirebaseAuth.AuthStateListener mAuthListner;
    public DatabaseReference myRef;
    public String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_step_counter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Declare Database variables
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
                    Toast.makeText(Navigation_stepCounter.this ,"Successful Sign in with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Navigation_stepCounter.this ,"Successful Signout",Toast.LENGTH_SHORT).show();
                }
            }

        };

        tv_steps =(TextView) findViewById(R.id.tv_steps);
        sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor countsensor= sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countsensor!=null)
        {
            sensormanager.registerListener(this,countsensor,SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        running=false;
        //sensormanager.unregisterListener(this,);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running)
        {
            tv_steps.setText(String.valueOf(sensorEvent.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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
        getMenuInflater().inflate(R.menu.navigation_step_counter, menu);
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
            Intent i =new Intent(Navigation_stepCounter.this,Navigation_WaterIntakeActivity.class);
            startActivity(i);
            // Handle the camera action
        }
        if (id == R.id.nav_settings) {
            Intent i =new Intent(Navigation_stepCounter.this,NavigationSettings.class);
            startActivity(i);
            // Handle the camera action
        }else if (id == R.id.nav_alarm) {
            Intent i =new Intent(Navigation_stepCounter.this,Navigation_reminder.class);
            startActivity(i);


        } else if (id == R.id.nav_step) {
            Intent i =new Intent(Navigation_stepCounter.this,Navigation_stepCounter.class);
            startActivity(i);


        } else if (id == R.id.nav_tips) {
            Intent i =new Intent(Navigation_stepCounter.this,Navigation_tips.class);
            startActivity(i);


        }else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Toast.makeText(this,"Successful Signout",Toast.LENGTH_SHORT).show();
            SaveSharedPreference.clearUserName(Navigation_stepCounter.this);

            Intent i = new Intent(Navigation_stepCounter.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);



        }  else if (id == R.id.nav_diet) {
            Intent i = new Intent(Navigation_stepCounter.this,DietActivity.class);
            //  i.putExtra("id",bmr);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
