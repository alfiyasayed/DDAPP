package com.example.dell.DDAPP;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class Navigation_WaterIntakeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button2;
    PieChart pieChart;
    Float water = 0f,glass = 0f;
    int nowater;
    PieData data;
    TextView tv;
    String weight,g;
    DatabaseHelper obj,obj1,obj2;
    String water1="",glass1="",data1="";
    int i;
    int label;

    //Firebase databse
   private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference myRef;
    public String UserId;

    //Date
    Calendar calendar=Calendar.getInstance();
    int Day=calendar.get(Calendar.DAY_OF_MONTH);
    String Day1=String.valueOf(Day);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__water_intake);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //
        button2 = findViewById(R.id.button2);
        tv = findViewById(R.id.textView2);


        //Declare Database variables
        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();


        tv.setText(UserId);

        //Toast.makeText(Navigation_WaterIntakeActivity.this, ""+UserId, Toast.LENGTH_SHORT).show();*/


        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Navigation_WaterIntakeActivity.this, "Successful Sign in with " + user.getUid(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Navigation_WaterIntakeActivity.this, "Successful Signout", Toast.LENGTH_SHORT).show();
                }
            }

        };
        Toast.makeText(Navigation_WaterIntakeActivity.this, "" + user.getUid(), Toast.LENGTH_LONG).show();


       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(Navigation_WaterIntakeActivity.this, ""+UserId, Toast.LENGTH_SHORT).show();
                //showData(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



      /*  Intent i1 = getIntent();
        String name = i1.getStringExtra("aaa");*/
        if(label==0) {
           obj1 = new DatabaseHelper(Navigation_WaterIntakeActivity.this);
            Cursor cursor0 = obj1.SearchGlucose(UserId);
            i = 0;
            while (cursor0.moveToNext()) {
                g = cursor0.getString(i);
                i++;
            }

        }
        if(label==1)
        {
            obj2 = new DatabaseHelper(Navigation_WaterIntakeActivity.this);
            Cursor cursor2 = obj2.SearchGlucose(UserId);
            i = 0;
            while (cursor2.moveToNext()) {
                g = cursor2.getString(i);
                i++;
            }
        }

       if(Float.valueOf(g)>Float.valueOf(6))
        {
           // Toast.makeText(Navigation_WaterIntakeActivity.this, "notification"+g, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Navigation_WaterIntakeActivity.this, GlucoseNotify.class);
            startActivity(intent);
            label=1;


        }
        obj=new DatabaseHelper(Navigation_WaterIntakeActivity.this);
        obj.chkFoodTable();
        obj.insertfooddata();
        obj.insertFood_typedata();

        Cursor cursor = obj.searchwater(UserId);
        i = 0;
        while (cursor.moveToNext()) {
            data1 = cursor.getString(i);
            i++;
        }
        if (data1.equals(""))
        {
              GetData(0f, 100f);

           // Toast.makeText(Navigation_WaterIntakeActivity.this ,"insideeeee 1 if           ",Toast.LENGTH_SHORT).show();

            cursor = obj.searchweight(user.getUid());

            i = 0;

            while (cursor.moveToNext()) {
                weight = cursor.getString(i);

                i++;
            }

            float ltr = 0;

            try {
                ltr = (((Float.parseFloat(weight) * 2.20462f) * .66f) * 0.0295735f);
            } catch (Exception e) {
               // Toast.makeText(Navigation_WaterIntakeActivity.this, "" + e, Toast.LENGTH_LONG).show();
            }

            nowater = (int) (ltr * 10) / 4;
            glass = (float) nowater;

            tv.setText(String.valueOf(glass) + " No . of glasses to b drink in a day");
            GetData(water, glass);
            water1 = water.toString();
            glass1 = glass.toString();
            obj.updatewater(UserId, water1, glass1,Day1);

            if (glass != 0f) {
                Buttoncall();
            } else {
                button2.setEnabled(false);
            }


        }
        else{
            //Toast.makeText(Navigation_WaterIntakeActivity.this ,"insideeeee 1 else           ",Toast.LENGTH_SHORT).show();


            cursor =obj.searchwaterDate(UserId);
            i = 0;
            while (cursor.moveToNext()) {
                data1  = cursor.getString(i);
                i++;
            }
            if(data1.equals(Day1))
            {
                //Toast.makeText(Navigation_WaterIntakeActivity.this ,"insideeeee 2 if           ",Toast.LENGTH_SHORT).show();

                cursor = obj.searchwater(UserId);
                i = 0;
                while (cursor.moveToNext()) {
                    data1  = cursor.getString(i);
                    i++;
                }
                water=Float.valueOf(data1);
                cursor =obj.searchglass(UserId);
                i = 0;

                while (cursor.moveToNext()) {
                    data1 = cursor.getString(i);

                    i++;
                }
                glass=Float.valueOf(data1);
           /* water-=1f;
            glass-=1f;*/
                tv.setText(String.valueOf(glass) + " No . of glasses to b drink in a day");
                GetData(water, glass);

                if (glass != 0f) {
                    Buttoncall();
                } else {
                    button2.setEnabled(false);
                }


            }
            else{
                GetData(0f, 100f);

               // Toast.makeText(Navigation_WaterIntakeActivity.this ,"insideeeee 2 else           ",Toast.LENGTH_SHORT).show();

                cursor = obj.searchweight(user.getUid());

                i = 0;

                while (cursor.moveToNext()) {
                    weight = cursor.getString(i);

                    i++;
                }

                float ltr = 0;

                try {
                    ltr = (((Float.parseFloat(weight) * 2.20462f) * .66f) * 0.0295735f);
                } catch (Exception e) {
                   // Toast.makeText(Navigation_WaterIntakeActivity.this, "" + e, Toast.LENGTH_LONG).show();
                }

                nowater = (int) (ltr * 10) / 4;
                glass = (float) nowater;

                tv.setText(String.valueOf(glass) + " No . of glasses to b drink in a day");
                GetData(water, glass);
                water1 = water.toString();
                glass1 = glass.toString();
                obj.updatewater(UserId, water1, glass1,Day1);

                if (glass != 0f) {
                    Buttoncall();
                } else {
                    button2.setEnabled(false);
                }

            }

        }

    }

















/*
//STart of the firebase data retrieval

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()) {
            UserInfo uInfo = new UserInfo();
            Toast.makeText(Navigation_WaterIntakeActivity.this, "" + UserId, Toast.LENGTH_SHORT).show();
            try {
               String wt=ds.child(UserId).getValue(UserInfo.class).toString();
                Toast.makeText(Navigation_WaterIntakeActivity.this, "" + wt, Toas.LENGTH_SHORT).show();
               uInfo=ds.getValue(UserInfo.class);

               uInfo.setWeight(ds.child(UserId).getValue(UserInfo.class).getWeight());
               // Toast.makeText(Navigation_WaterIntakeActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                tv.setText("Hello");

                uInfo.setHeight(ds.child(UserId).getValue(UserInfo.class).getHeight());
            } catch (Exception e) {
                Toast.makeText(Navigation_WaterIntakeActivity.this, "" + e, Toast.LENGTH_SHORT).show();


            }
           // String height = uInfo.getHeight();
            Toast.makeText(Navigation_WaterIntakeActivity.this, "" + uInfo.getHeight(), Toast.LENGTH_SHORT).show();

            String weight = uInfo.getWeight();
            Toast.makeText(Navigation_WaterIntakeActivity.this, "" + weight, Toast.LENGTH_SHORT).show();
            float ltr;
            ltr = (((Float.parseFloat(weight) * 2.20462f) * .66f) * 0.0295735f);
            nowater = (int) (ltr * 10) / 4;
            glass = (float) nowater;

            tv.setText(String.valueOf(glass) + " No . of glasses to b drink in a day");
            GetData(water, glass);
            if (glass != 0f) {
                Buttoncall();
            } else {
                button2.setEnabled(false);
            }
            }
            }

            */


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
        getMenuInflater().inflate(R.menu.navigation__water_intake, menu);
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

            Intent i =new Intent(Navigation_WaterIntakeActivity.this,Navigation_WaterIntakeActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_settings) {
            Intent i =new Intent(Navigation_WaterIntakeActivity.this,NavigationSettings.class);
            startActivity(i);

        }
        else if (id == R.id.nav_alarm) {
            Intent i =new Intent(Navigation_WaterIntakeActivity.this,Navigation_reminder.class);
            startActivity(i);

        }else if (id == R.id.nav_step) {
            Intent i =new Intent(Navigation_WaterIntakeActivity.this,Navigation_stepCounter.class);
            startActivity(i);


        }
        else if (id == R.id.nav_tips) {
            Intent i =new Intent(Navigation_WaterIntakeActivity.this,Navigation_tips.class);
            startActivity(i);


        }else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Toast.makeText(this,"Successful Signout",Toast.LENGTH_SHORT).show();
            SaveSharedPreference.clearUserName(Navigation_WaterIntakeActivity.this);

            Intent i = new Intent(Navigation_WaterIntakeActivity.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);


        } else if (id == R.id.nav_diet) {
            Intent i = new Intent(Navigation_WaterIntakeActivity.this,DietActivity.class);
            //  i.putExtra("id",bmr);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    void Buttoncall() {

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (glass > 0) {
                    water += 1f;
                    glass -= 1f;
                    GetData(water, glass);
                    water1=water.toString();
                    glass1=glass.toString();
                    obj.updatewater(UserId,water1,glass1,Day1);
                    tv.setText(String.valueOf(glass) + " No . of glasses remains to drink for today");
                    pieChart.notifyDataSetChanged();
                    pieChart.invalidate();
                } else {

                    obj.updatewater(UserId,water1,glass1,Day1);

                    tv.setText(" Todays reqirement of water fulfill now");
                    button2.setEnabled(false);

                }

            }
        });
    }


    void GetData(Float water,Float glass) {
        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(15, 10, 15, 25);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(6f);

        ArrayList<PieEntry> yvalues = new ArrayList<>();

        yvalues.add(new PieEntry(water, " % Water   Intake"));
        yvalues.add(new PieEntry(glass, " % Glass remains"));

        PieDataSet dataset = new PieDataSet(yvalues, "Count");
        dataset.setSliceSpace(0f);
        dataset.setSelectionShift(5f);

        final int[] MY_COLORS = {Color.rgb(0,0,255), Color.rgb(255,0,0)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);

        dataset.setColors(colors);

        // dataset.setColors(ColorTemplate.getHoloBlue(),ColorTemplate.getholo_red_dark);
        data = new PieData(dataset);
        dataset.setValueTextSize(10f);
        dataset.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }





//

}
















