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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NavigationSettings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;
    public FirebaseAuth.AuthStateListener mAuthListner;
    public DatabaseReference myRef;
    public String UserId;
    public Long time,time1;
    AlarmManager alarmManager1;
    PendingIntent pendingIntent1;
    Button Submit ;
    EditText weight ;
    EditText name ;
    EditText height ;
    EditText age ;
    EditText contact ;
    EditText glucose ;
    String ans="";
    public int g;

    RadioGroup radio ;
    RadioButton radio1,radio2;

    DatabaseHelper obj;



    float weight1;
    float height1;
    float age1;
    float bmr1;
    float bmi1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        obj = new DatabaseHelper(NavigationSettings.this);

        mAuth = FirebaseAuth.getInstance();
        // Toast.makeText(Navigation_WaterIntakeActivity.this, "Hello", Toast.LENGTH_SHORT).show();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        // UserId = user.getUid();
        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(NavigationSettings.this ,"Successful Sign in with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NavigationSettings.this ,"Successful Signout",Toast.LENGTH_SHORT).show();
                }
            }

        };


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Submit = (Button) findViewById(R.id.buttonSubmit);
        weight = (EditText) findViewById(R.id.editText3);
        age = (EditText)findViewById(R.id.editText4);
        height =(EditText) findViewById(R.id.editText2);
        name =(EditText)findViewById(R.id.editText1);
        glucose =(EditText)findViewById(R.id.editText5);
        contact =(EditText)findViewById(R.id.editText6);
        radio =(RadioGroup) findViewById(R.id.radio);
        radio1 =(RadioButton) findViewById(R.id.radio1);
        radio2 =(RadioButton) findViewById(R.id.radio2);

        Intent intent = new Intent(NavigationSettings.this, Notify.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(NavigationSettings.this, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
       // Toast.makeText(NavigationSettings.this ,"time"+time,Toast.LENGTH_SHORT).show();


        if (calendar.AM_PM == 0)
            time = Long.valueOf(time+(40* (1000 * 60 * 60 * 12)));
        else
            time = Long.valueOf(time+(20* (1000 * 60 * 60 * 24)));
        alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP,time , time, pendingIntent1);

 Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               // Toast.makeText(getApplicationContext(),"Nooooo",Toast.LENGTH_LONG);
                if(name.getText().toString().equals("") || height.getText().toString().equals("") || weight.getText().toString().equals("") || age.getText().toString().equals("") || contact.getText().length()!=10|| contact.getText().toString().equals("")){
                    Toast.makeText(NavigationSettings.this,"Please Enter all details",Toast.LENGTH_LONG);
                }else
                {
                    weight1 = Float.parseFloat(weight.getText().toString());
                    height1 = Float.parseFloat(height.getText().toString());
                    age1 = Float.parseFloat(age.getText().toString());
                    g=radio.getCheckedRadioButtonId();
                   switch (g) {
                        case R.id.radio1:
                            bmr1 = (float) (66 + (13.7 * weight1) + (5 * (height1 / 100)) - (6.8 * age1));
                            ans=radio1.getText().toString();
                            break;
                        case R.id.radio2:
                            bmr1 = (float) (655 + (9.6 * weight1) + (1.8 * (height1 / 100))- (4.7 * age1));
                            ans=radio2.getText().toString();
                            break;
                    }


                    height1=height1/100;
                    height1 = height1*height1*height1;
                    bmi1=weight1/height1;
                    Toast.makeText(NavigationSettings.this, "" +bmr1, Toast.LENGTH_LONG).show();
                    Toast.makeText(NavigationSettings.this, "" +bmi1, Toast.LENGTH_LONG).show();
                    String weight1 = weight.getText().toString();
                    String height1 = height.getText().toString();
                    String age1 =age.getText().toString();
                    String name1 =name.getText().toString();
                    String phone1 =contact.getText().toString();
                    String glucose1 =glucose.getText().toString();
                    String bmr= String.valueOf(bmr1);
                    String bmi= String.valueOf(bmi1);


                    FirebaseUser user = mAuth.getCurrentUser();
                    String UserId = user.getUid();

                    obj.UpdateUserData(UserId,name1,weight1,height1,age1,ans,glucose1,phone1,bmr,bmi);

                        int g=Integer.parseInt(glucose1);


                    if(g>6)
                    {
                       // Toast.makeText(NavigationSettings.this, "notification"+g, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(NavigationSettings.this, GlucoseNotify.class);
                        startActivity(intent);


                    }



               /* FirebaseUser user = mAuth.getCurrentUser();
                    String UserId = user.getUid();
                    myRef.child(UserId).child("Name").setValue(name);
                  myRef.child(UserId).child("Age").setValue(age1);
                    myRef.child(UserId).child("BMR").setValue(bmr1);
                   myRef.child(UserId).child("Height").setValue(height1);
                   myRef.child(UserId).child("Weight").setValue(weight1);
                   myRef.child(UserId).child("Gender").setValue(ans);
                   myRef.child(UserId).child("BMI").setValue(bmi1);
                   startActivity(new Intent(BMRActivity.this,LoginActivity.class));*/

                }
            }
        });

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
        getMenuInflater().inflate(R.menu.navigation_settings, menu);
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
            Intent i =new Intent(NavigationSettings.this,Navigation_WaterIntakeActivity.class);
            startActivity(i);
            // Handle the camera action
        }
        if (id == R.id.nav_settings) {
            Intent i =new Intent(NavigationSettings.this,NavigationSettings.class);
            startActivity(i);
            // Handle the camera action
        }else if (id == R.id.nav_alarm) {
            Intent i =new Intent(NavigationSettings.this,Navigation_reminder.class);
            startActivity(i);


        } else if (id == R.id.nav_step) {
            Intent i =new Intent(NavigationSettings.this,Navigation_stepCounter.class);
            startActivity(i);


        }else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Toast.makeText(this,"Successful Signout",Toast.LENGTH_SHORT).show();
            SaveSharedPreference.clearUserName(NavigationSettings.this);

            Intent i = new Intent(NavigationSettings.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);



        }  else if (id == R.id.nav_diet) {
            Intent i = new Intent(NavigationSettings.this,DietActivity.class);
            //  i.putExtra("id",bmr);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
