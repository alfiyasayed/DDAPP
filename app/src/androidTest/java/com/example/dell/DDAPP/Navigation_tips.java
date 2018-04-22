package com.example.dell.DDAPP;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Navigation_tips extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;
    public FirebaseAuth.AuthStateListener mAuthListner;
    public DatabaseReference myRef;
    public String UserId;


    ListView simpleList;
    String countryList[] = { "Health Tips","",
            "Manage stress",
            "Excess stress can elevate blood sugar levels. But you can find relief by sitting quietly for 15 minutes, meditating, or practicing yoga.",
            "",
            "Catch some ZZZs",
            " People with diabetes who get enough sleep often have healthier eating habits and improved blood sugar levels."
            ,"",
            "Check your blood glucose ",
            "By checking your blood glucose, you can become aware of what affects your levels, and may be able to catch problems before they get out of hand.",
            "",
            "Prevent sores ",
            "If you are having problems with your feet or find a sore that isn’t healing, speak with your doctor immediately.","",

            "Exercise Tips","",
            "Try quick workouts",
            "If you can do your exercise in one 30 minute stretch, fine. But if not, break it up into increments you can manage that add up to at least 30 minutes each day.","",
            "Focus on overall activity",
            "Increase activity in general-such as walking or climbing stairs-rather than a particular type of exercise.","",
           " Work out with a friend",
            "Having a friend call or setting up an exercise'contract' with a buddy may help. ","",
            "Set specific, attainable goals",
            "For example, you might set a goal of walking 10 minutes every Monday, Wednesday, and Saturday.","",
            "Write it all down",
            "Record on your calendar every day whether you exercised for 10 or 15 minutes or more.","",
            "Don't set goals too high",
            "It's much better to set a lower goal and be successful at it, You are much more likely to be successful if you start with small, easily attainable goals and gradually increase them.","",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_tips);
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
                    Toast.makeText(Navigation_tips.this ,"Successful Sign in with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Navigation_tips.this ,"Successful Signout",Toast.LENGTH_SHORT).show();
                }
            }

        };


        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.tv1, countryList);
        simpleList.setAdapter(arrayAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation_tips, menu);
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
            Intent i =new Intent(Navigation_tips.this,Navigation_WaterIntakeActivity.class);
            startActivity(i);
            // Handle the camera action
        }
        if (id == R.id.nav_settings) {
            Intent i =new Intent(Navigation_tips.this,NavigationSettings.class);
            startActivity(i);
            // Handle the camera action
        }else if (id == R.id.nav_alarm) {
            Intent i =new Intent(Navigation_tips.this,Navigation_reminder.class);
            startActivity(i);


        } else if (id == R.id.nav_step) {
            Intent i =new Intent(Navigation_tips.this,Navigation_stepCounter.class);
            startActivity(i);


        } else if (id == R.id.nav_tips) {
            Intent i =new Intent(Navigation_tips.this,Navigation_tips.class);
            startActivity(i);


        }else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Toast.makeText(this,"Successful Signout",Toast.LENGTH_SHORT).show();
            SaveSharedPreference.clearUserName(Navigation_tips.this);

            Intent i = new Intent(Navigation_tips.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);



        }  else if (id == R.id.nav_diet) {
            Intent i = new Intent(Navigation_tips.this,DietActivity.class);
            //  i.putExtra("id",bmr);
            startActivity(i);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
