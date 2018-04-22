package com.example.dell.DDAPP;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Diet_plan extends AppCompatActivity {
    String food, exist, roti, roti_cal, gravy, gravy_cal, lunch;


    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;
    public FirebaseAuth.AuthStateListener mAuthListner;
    public DatabaseReference myRef;
    public String UserId, UserName, UserGender, UserBmr;
    public float calories, calories_breakfast, calories_lunch, calories_dinner, calories_snacks;
    public double cl_roti, cl_gravy, cl_sabzi;
    public  String Today,Now,roti1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        TextView tv4 = findViewById(R.id.tv4);
        Now=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      //  Toast.makeText(Diet_plan.this, "Now" + Now, Toast.LENGTH_SHORT).show();

      //  Toast.makeText(Diet_plan.this, "IF"+roti, Toast.LENGTH_SHORT).show();


        if(SaveSharedPreference.getVar(Diet_plan.this).equals("yes")) {
            roti1=SaveSharedPreference.getUserGlass(Diet_plan.this);
           // Toast.makeText(Diet_plan.this, "ELSE" +roti1, Toast.LENGTH_SHORT).show();


            tv4.setText(roti1);





        }
        else{
    Dietplan dp = new Dietplan(Diet_plan.this);
    //  dp.dietdata(Diet_plan.this);
    try {
        Cursor cursor = dp.Selectroti();

        int i = 0;

        while (cursor.moveToNext()) {
            roti = cursor.getString(i);
            i++;
            tv4.setText(roti);
            SaveSharedPreference.setUserGlass(Diet_plan.this,roti);
          //  Toast.makeText(Diet_plan.this, "ELSE" +roti, Toast.LENGTH_SHORT).show();

            roti1=SaveSharedPreference.getUserGlass(Diet_plan.this);
          //  Toast.makeText(Diet_plan.this, "ELSE" +roti1, Toast.LENGTH_SHORT).show();




         //   Toast.makeText(Diet_plan.this, "ELSE" +Today, Toast.LENGTH_SHORT).show();




        }
    } catch (Exception e) {
      //  Toast.makeText(Diet_plan.this, "error" + e, Toast.LENGTH_SHORT).show();

    }
}

/*

        mAuth = FirebaseAuth.getInstance();
        // Toast.makeText(Navigation_WaterIntakeActivity.this, "Hello", Toast.LENGTH_SHORT).show();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();

UserName=SaveSharedPreference.getUserName(Diet_plan.this);
UserGender=SaveSharedPreference.getUserGender(Diet_plan.this);
UserBmr=SaveSharedPreference.getUserBmr(Diet_plan.this);

if(UserGender=="Male")
{
    calories=2300;
}
else
{
    calories=1900;

}

if(Float.parseFloat(UserBmr)>calories)
{
    calories=Float.parseFloat(UserBmr);
}

        Toast.makeText(Diet_plan.this, UserGender+"shared preference" +UserName , Toast.LENGTH_SHORT).show();

calories_breakfast=(calories*36)/100;
calories_lunch=(calories*27)/100;
calories_dinner=(calories*27)/100;
calories_snacks=(calories*10)/100;


*/
///////////////////////////////////////////////////////////////////////////////////////
            /*
calories_lunch=500;

cl_roti=(calories_lunch)*0.4;
cl_gravy=(calories_lunch)*0.3;
cl_sabzi=(calories_lunch)*0.3;


        Dietplan dp = new Dietplan(Diet_plan.this);
        //  dp.dietdata(Diet_plan.this);
try {
    Cursor cursor = dp.Selectroti();

    int i = 0;

    while (cursor.moveToNext()) {
        roti = cursor.getString(i);
        i++;
    }


    Toast.makeText(Diet_plan.this, "random data" +roti, Toast.LENGTH_SHORT).show();


    try {
        Cursor cursor1 =  dp.Getdata(roti);

        int ii = 0;

        while (cursor1.moveToNext()) {
            roti_cal = cursor1.getString(ii);
            ii++;
        }

        Toast.makeText(Diet_plan.this, "random data" +roti_cal, Toast.LENGTH_SHORT).show();
        dp.savedata(roti,lunch);

        if(Double.parseDouble(roti_cal)<cl_roti)
        {
            dp.savedata(roti,lunch);
        }

    }
    catch (Exception e)
    {
        Toast.makeText(Diet_plan.this, "calorie" +e, Toast.LENGTH_SHORT).show();

    }

}
catch (Exception e)
{
    Toast.makeText(Diet_plan.this, "roti" +e, Toast.LENGTH_SHORT).show();

}

calories_lunch=calories_lunch-(2*Float.parseFloat(roti_cal));
        try {
            Cursor cursor2 =  dp.Selectgravy();

            int iii = 0;

            while (cursor2.moveToNext()) {
                gravy = cursor2.getString(iii);
                iii++;
            }

            Toast.makeText(Diet_plan.this, "random gravy" +gravy, Toast.LENGTH_SHORT).show();


        }
        catch (Exception e)
        {
            Toast.makeText(Diet_plan.this, "calorie" +e, Toast.LENGTH_SHORT).show();

        }

        try {
            Cursor cursor3 =  dp.Getdata(gravy);

            int iiii = 0;

            while (cursor3.moveToNext()) {
                gravy_cal = cursor3.getString(iiii);
                iiii++;
            }

            Toast.makeText(Diet_plan.this, "random data" +gravy_cal, Toast.LENGTH_SHORT).show();
            //dp.Getdata(food);

        }
        catch (Exception e)
        {
            Toast.makeText(Diet_plan.this, "gravy_calorie error" +e, Toast.LENGTH_SHORT).show();

        }

calories_lunch=calories_lunch-Float.parseFloat(gravy_cal);
        Toast.makeText(Diet_plan.this, "remaining calorie" +calories_lunch, Toast.LENGTH_SHORT).show();*/

/*

        TextView tv4 = findViewById(R.id.tv4);

        Dietplan dp = new Dietplan(Diet_plan.this);
      //  dp.dietdata(Diet_plan.this);

        Cursor cursor = dp.Selectdietdata();

        int i = 0;

        while (cursor.moveToNext()) {
            food = cursor.getString(i);
            i++;
        }


        Toast.makeText(Diet_plan.this, "random data" + food, Toast.LENGTH_SHORT).show();


        try {
            Cursor cursor1 = dp.existdata(food);

            if (cursor1.getCount() <= 0) {
                Toast.makeText(Diet_plan.this, "null" +food, Toast.LENGTH_SHORT).show();
                dp.savedata(food);
            }

            else {

                Cursor cursor2 = dp.Selectdietdata();
                int ii = 0;

                while (cursor2.moveToNext()) {
                    food = cursor2.getString(i);
                    i++;
                }
                Toast.makeText(Diet_plan.this, "not null" + food, Toast.LENGTH_SHORT).show();
                dp.savedata(food);
            }
        }
        catch (Exception e) {
            Toast.makeText(Diet_plan.this, "exist error" + e, Toast.LENGTH_SHORT).show();

        }


/*





        int ii = 0;

        while (cursor1.moveToNext()) {
            exist = cursor1.getString(i);
            i++;
        }




        List<String> list=new ArrayList<String>();
        Cursor c1=dp.Selectdietdata();
        int n=0;
        while (c1.moveToNext()){
            list.add(c1.getString(n));
            n++;

        }

        StringBuilder builder = new StringBuilder();
        for (String data : list) {
            builder.append(data + "");
        }

        tv4.setText(builder.toString());

       // dp.clearData();
     /*
        for(int a=0; a<list.size();a++)
        {
            tv4.setText((CharSequence) list);
        }*/


    }
}
