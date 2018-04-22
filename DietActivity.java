package com.example.dell.DDAPP;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DietActivity extends AppCompatActivity   {

   //Databse Variables
    DatabaseHelper obj ;
    Double totalcalory,prebreakfast,breakfast,lunch,roti1,rice1,gravy1,snacks,dinner,roti2,gravy2,calory;
    boolean flag=false;
    int i=0;String data="";String data1="";String data2="";String foodtype="";
    int PreRow,BreRow,LunchRow,DinnerRow;
    String Prearray[];
    String Breakarray[]=new String[30];
    String Luncharray[]=new String[10];
    String Dinnerarray[]=new String[10];

    //Firebase databse
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference myRef;
    public String UserId;
    int id;


    //Recycler View
    private List<Tutorial> tutorialList=new ArrayList<>();
    private RecyclerView recyclerView;
    private TutorialsAdapter tutorialsAdapter;


    //Date
    Calendar calendar=Calendar.getInstance();
   int Day=calendar.get(Calendar.DAY_OF_MONTH);
    String Day1=String.valueOf(Day);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //Declare Database variables
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        UserId = user.getUid();

        Prearray =new String [10];

        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Toast.makeText(DietActivity.this ,"Successful Sign in with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DietActivity.this ,"Successful Signout",Toast.LENGTH_SHORT).show();
                }
            }

        };

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        tutorialsAdapter = new TutorialsAdapter(tutorialList);
        RecyclerView.LayoutManager mlayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tutorialsAdapter);
        obj = new DatabaseHelper(DietActivity.this);

        Cursor cursor =obj.countdietrow(UserId);
        i = 0;
        while (cursor.moveToNext()) {
            data  = cursor.getString(i);
            i++;
        }
        int row=Integer.valueOf(data);
         if(row==0)
        {
           loadTutorials();

        }
    else {
          cursor =obj.searchUserDate(UserId,1);
            i = 0;
            while (cursor.moveToNext()) {
                data  = cursor.getString(i);
                i++;
            }
            // Toast.makeText(DietActivity.this ,"insideeeee first else           "+Day1,Toast.LENGTH_SHORT).show();

             if(data.equals(Day1))
            {
                //Toast.makeText(DietActivity.this ,"insideeeee second if           ",Toast.LENGTH_SHORT).show();
            //PreBreakfast
                cursor =obj.countdietPrerow(UserId,"PreBreakFast");
                i = 0;
                while (cursor.moveToNext()) {
                    data1 = cursor.getString(i);
                    i++;
                }
                PreRow=Integer.parseInt(data1);
            //Breakfast
            cursor =obj.countdietBrerow(UserId,"BreakFast");
                i = 0;
                while (cursor.moveToNext()) {
                    data1 = cursor.getString(i);
                    i++;
                }
                BreRow=Integer.parseInt(data1);
                //Lunch
                cursor =obj.countdietLunchrow(UserId,"Lunch");
                i = 0;
                while (cursor.moveToNext()) {
                    data1 = cursor.getString(i);
                    i++;
                }
                LunchRow=Integer.parseInt(data1);
                //Dinner
                cursor =obj.countdietDinnerrow(UserId,"Dinner");
                i = 0;
                while (cursor.moveToNext()) {
                    data1 = cursor.getString(i);
                    i++;
                }
                DinnerRow=Integer.parseInt(data1);


                //PreBreakfast
                tutorialList.add(new Tutorial("**** PREBREAKFAST ****" ,""));

                for(int k=1;k<=PreRow;k++) {


                    cursor = obj.searchDietFood(UserId,  k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data1 = cursor.getString(i);
                        i++;
                    }
                    cursor = obj.searchDietCalory(UserId,  k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data2 = cursor.getString(i);
                        i++;

                    }
                    tutorialList.add(new Tutorial(data1, data2 + " calories"));

                }
                //Breakfast
                tutorialList.add(new Tutorial("**** BREAKFAST ****" ,""));
                for(int k=(PreRow+1);k<=(PreRow+BreRow);k++) {


                    cursor = obj.searchDietFood(UserId,  k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data1 = cursor.getString(i);
                        i++;
                    }
                    cursor = obj.searchDietCalory(UserId,  k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data2 = cursor.getString(i);
                        i++;
                    }
                    tutorialList.add(new Tutorial(data1, data2 + " calories"));

                }
                    //Lunch
                    tutorialList.add(new Tutorial("**** Lunch ****" ,""));
                for(int k=(PreRow+BreRow+1);k<=(PreRow+BreRow+LunchRow);k++) {
                    cursor = obj.searchDietFood(UserId,  k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data1 = cursor.getString(i);
                        i++;
                    }
                    cursor = obj.searchDietCalory(UserId, k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data2 = cursor.getString(i);
                        i++;
                    }
                    tutorialList.add(new Tutorial(data1, data2 + " calories"));

                }
                    //Dinner
                    tutorialList.add(new Tutorial("**** DINNER ****" ,""));
                for(int k=(PreRow+BreRow+LunchRow+1);k<=(PreRow+BreRow+LunchRow+DinnerRow);k++) {
                    cursor = obj.searchDietFood(UserId,  k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data1 = cursor.getString(i);
                        i++;
                    }
                    cursor = obj.searchDietCalory(UserId, k);
                    i = 0;
                    while (cursor.moveToNext()) {
                        data2 = cursor.getString(i);
                        i++;
                    }
                    tutorialList.add(new Tutorial(data1, data2 + " calories"));

                }
                }
             else
             {
                // Toast.makeText(DietActivity.this ,"insideeeee second else        ",Toast.LENGTH_SHORT).show();
                 obj.deleteDietRow(UserId);
                 loadTutorials();
             }
            }

        }

    void loadTutorials(){

        obj = new DatabaseHelper(DietActivity.this);

        Cursor cursor = obj.searchgender(UserId);
        i = 0;
        while (cursor.moveToNext()) {
            data  = cursor.getString(i);
            i++;
        }
        if(data=="Male")
        {
            totalcalory=2300d;
        }
        else
        {
            totalcalory=1900d;
        }
        cursor = obj.searchbmr(UserId);
        i = 0;

        while (cursor.moveToNext()) {
            data  = cursor.getString(i);
            i++;
        }
        if(Double.parseDouble(data)>totalcalory)
        {
            totalcalory=Double.parseDouble(data);

        }

        prebreakfast= totalcalory*0.05;
        breakfast=totalcalory*0.33;
      //  Toast.makeText(DietActivity.this ,"gjhjkj            "+breakfast,Toast.LENGTH_SHORT).show();
        lunch=totalcalory*.31;

        roti1=lunch*0.3;
        rice1=lunch*0.3;
        gravy1=lunch*0.4;

        //snacks= totalcalory*0.09;


        dinner=totalcalory*.31;

        roti2=dinner*0.5;
        gravy2=dinner*0.5;



        /*obj.chkFoodTable();
        obj.insertfooddata();
        obj.insertFood_typedata();*/


        Cursor cursor1= obj.searchUserFoodType(UserId);
        i=0;
        while (cursor1.moveToNext())
        {
            foodtype=cursor1.getString(i);
            i++;
        }

     //PreBreakfastData
      tutorialList.add(new Tutorial("**** PREBREAKFAST ****" ,""));

       int j=0;
       while(prebreakfast>30d)
        {

            obj.retrivePreBreakFastdata(foodtype);

            cursor1 = obj.displayFoodData();
            i = 0;
            data1 = "";
            while (cursor1.moveToNext()) {
                data1 = cursor1.getString(i);
                i++;
            }
            if(Arrays.asList(Prearray).contains(data1))
            {  flag = true;
                //Toast.makeText(DietActivity.this, " current  " +data1, Toast.LENGTH_SHORT).show();

            }



        if(flag==false) {
                Prearray[j]=data1;

            j++;
           // Toast.makeText(DietActivity.this, "PreBreakfast item " + j + ";- " + data1, Toast.LENGTH_SHORT).show();
            cursor1 = obj.displayCaloryData(data1);
            i = 0;
            data2 = "";
            while (cursor1.moveToNext()) {
                data2 = cursor1.getString(i);
                i++;
            }
            try {
                calory = Double.valueOf(data2);
            } catch (NumberFormatException e) {
              //  Toast.makeText(DietActivity.this, "NumberFormatException", Toast.LENGTH_SHORT).show();
            }
            tutorialList.add(new Tutorial(data1 ,data2+" calories"));
            obj.insertdiettable(UserId,data1 , data2,"PreBreakFast",Day1);
            prebreakfast -= calory;
           // Toast.makeText(DietActivity.this, "PreBreakfast item remaing caloy  " + prebreakfast, Toast.LENGTH_SHORT).show();

        }

        }

        //BreakFastData
      tutorialList.add(new Tutorial("****BREAKFAST****" ,""));

       j=0;

        while(breakfast>30d) {
            obj.retriveBreakFastdata(foodtype);

            cursor1 = obj.displayFoodData();
            i = 0;
            data1 = "";
            while (cursor1.moveToNext()) {
                data1 = cursor1.getString(i);
                i++;
            }
            if(Arrays.asList(Breakarray).contains(data1))
            {  flag = true;
                //Toast.makeText(DietActivity.this, " current  " +data1, Toast.LENGTH_SHORT).show();

                }


          /*  for (int k = 0; k < 30; k++) {
                if (Breakarray[k] == data1) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }*/
            if (flag == false) {
                Breakarray[j] = data1;
                    //Toast.makeText(DietActivity.this, " currenjkt  "  + Breakarray[j], Toast.LENGTH_SHORT).show();
                j++;
                //Toast.makeText(DietActivity.this, "Breakfast item " + j + ";- " + data1, Toast.LENGTH_SHORT).show();
            Cursor  cursor2 = obj.displayCaloryData(data1);
                i = 0;
                data2 = "";
                while (cursor2.moveToNext()) {
                    data2 = cursor2.getString(i);
                    i++;
                }
                try {
                    calory = Double.valueOf(data2);
                } catch (NumberFormatException e) {
                  //  Toast.makeText(DietActivity.this, "NumberFormatException", Toast.LENGTH_SHORT).show();
                }

                tutorialList.add(new Tutorial(data1 ,data2+" calories"));
                obj.insertdiettable(UserId,data1 , data2,"BreakFast",Day1);

                breakfast -= calory;
                //Toast.makeText(DietActivity.this, "Breakfast item remaing caloy  " + breakfast, Toast.LENGTH_SHORT).show();
            }

        }


      //Lunch Data Display
       //retrive lunch Rice

                tutorialList.add(new Tutorial("**** Lunch ****" ,""));

           obj.retriveRicedata(rice1,foodtype);
            cursor1 = obj.displayFoodData();
            i = 0;
            data1 = "";
            while (cursor1.moveToNext()) {
                data1 = cursor1.getString(i);
                i++;
            }
            //Toast.makeText(DietActivity.this, "Lunch Rice;- " + data1, Toast.LENGTH_SHORT).show();
            cursor1 = obj.displayCaloryData(data1);
            i = 0;
            data2 = "";
            while (cursor1.moveToNext()) {
                data2 = cursor1.getString(i);
                i++;
            }
            try {
                calory = Double.valueOf(data2);
            } catch (NumberFormatException e) {
              //  Toast.makeText(DietActivity.this, "NumberFormatException", Toast.LENGTH_SHORT).show();
            }
           rice1 -= calory;
            //Toast.makeText(DietActivity.this, "remianing calories of lunch rice first " +rice1, Toast.LENGTH_SHORT).show();
        i=2;
            while(50d<rice1 || rice1>0d)
            {
                rice1-=calory;

            //    Toast.makeText(DietActivity.this, "amount is "+i+" of " + data1, Toast.LENGTH_SHORT).show();
          //      Toast.makeText(DietActivity.this, "remianing calories of lunch rice second " +rice1, Toast.LENGTH_SHORT).show();
                i++;
            }
        tutorialList.add(new Tutorial(data1 ,data2+" calories"));
        obj.insertdiettable(UserId,data1 , data2,"Lunch",Day1);

        //Toast.makeText(DietActivity.this, "remianing calories of lunch rice final " +rice1, Toast.LENGTH_SHORT).show();


        //retrive lunch Roti
        obj.retriveRotidata(roti1,foodtype);
        Cursor cursor2 = obj.displayFoodData();
        i = 0;
        data1="";
        while (cursor2.moveToNext()) {
            data1 = cursor2.getString(i);
            i++;
        }
            //    Toast.makeText(DietActivity.this,"Lunch Roti:- "+data1,Toast.LENGTH_SHORT).show();
        cursor2 = obj.displayCaloryData(data1);
        i = 0;
        data2 = "";
        while (cursor2.moveToNext()) {
            data2 = cursor2.getString(i);
            i++;
        }

        try{
            calory=Double.valueOf(data2);}
        catch (NumberFormatException e)
        { //       Toast.makeText(DietActivity.this,"NumberFormatException",Toast.LENGTH_SHORT).show();
        }

        roti1 -= calory;
        //Toast.makeText(DietActivity.this, "remianing calories of lunch roti first" +roti1, Toast.LENGTH_SHORT).show();
        i=2;
        while(50d<roti1 || roti1>0d)
        {
            roti1-=calory;

            //Toast.makeText(DietActivity.this, "amount is "+i+" of" + data1, Toast.LENGTH_SHORT).show();
          //  Toast.makeText(DietActivity.this, "remianing calories of lunch roti second" +roti1, Toast.LENGTH_SHORT).show();
            i++;
        }
        tutorialList.add(new Tutorial(data1 ,data2+" calories"));
        obj.insertdiettable(UserId,data1 , data2,"Lunch",Day1);

        //Toast.makeText(DietActivity.this, "remianing calories of lunch roti final" +roti1, Toast.LENGTH_SHORT).show();

//retrive lunch gravy
        obj.retriveGravydata(gravy1,foodtype);
        cursor2 = obj.displayFoodData();;
        i = 0;
        data1="";
        while (cursor2.moveToNext()) {
            data1 = cursor2.getString(i);
            i++;
        }
        //Toast.makeText(DietActivity.this,"Lunch Gravy:- "+data1,Toast.LENGTH_SHORT).show();
        cursor2 = obj.displayCaloryData(data1);
        i = 0;
        data2 = "";
        while (cursor2.moveToNext()) {
            data2 = cursor2.getString(i);
            i++;
        }

        try{
            calory=Double.valueOf(data2);}
        catch (NumberFormatException e)
        { //       Toast.makeText(DietActivity.this,"NumberFormatException",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(DietActivity.this,"lunch gravy calory   "+calory,Toast.LENGTH_SHORT).show();


        gravy1 -= calory;
        //Toast.makeText(DietActivity.this, "remianing calories of lunch gravy first " +gravy1, Toast.LENGTH_SHORT).show();
        i=2;
        while(50d<gravy1 || gravy1>0d)
        {
            gravy1-=calory;

            //Toast.makeText(DietActivity.this, "amount is "+i+" of " + data1, Toast.LENGTH_SHORT).show();
          //  Toast.makeText(DietActivity.this, "remianing calories of lunch gravy second " +gravy1, Toast.LENGTH_SHORT).show();
            i++;
        }
        tutorialList.add(new Tutorial(data1 ,data2+" calories"));
        obj.insertdiettable(UserId,data1 , data2,"Lunch",Day1);

        //Toast.makeText(DietActivity.this, "remianing calories of lunch gravy final " +gravy1, Toast.LENGTH_SHORT).show();





        //Dinner Data Display
        tutorialList.add(new Tutorial("**** Dinner ****" ,""));

        //retrive Dinner Roti
        obj.retriveRotidata(roti2,foodtype);
        cursor2 = obj.displayFoodData();
        i = 0;
        data1="";
        while (cursor2.moveToNext()) {
            data1 = cursor2.getString(i);
            i++;
        }
        //Toast.makeText(DietActivity.this,"Dinner Roti:- "+data1,Toast.LENGTH_SHORT).show();
        cursor2 = obj.displayCaloryData(data1);
        i = 0;
        data2 = "";
        while (cursor2.moveToNext()) {
            data2 = cursor2.getString(i);
            i++;
        }

        try{
            calory=Double.valueOf(data2);}
        catch (NumberFormatException e)
        { //       Toast.makeText(DietActivity.this,"NumberFormatException",Toast.LENGTH_SHORT).show();
        }

       roti2 -= calory;
        //Toast.makeText(DietActivity.this, "remianing calories of dinner roti first " +roti2, Toast.LENGTH_SHORT).show();
        i=2;
        while(50d<roti2 || roti2>0d)
        {
            roti2-=calory;

           // Toast.makeText(DietActivity.this, "amount is "+i+" of " + data1, Toast.LENGTH_SHORT).show();
          //  Toast.makeText(DietActivity.this, "remianing calories of dinner roti second " +roti2, Toast.LENGTH_SHORT).show();
            i++;
        }
        tutorialList.add(new Tutorial(data1 ,data2+" calories"));
        obj.insertdiettable(UserId,data1 , data2,"Dinner",Day1);

        //Toast.makeText(DietActivity.this, "remianing calories of dinner roti final " +roti2, Toast.LENGTH_SHORT).show();

//retrive Dinner  gravy
        obj.retriveGravydata(gravy2,foodtype);
        cursor2 = obj.displayFoodData();;
        i = 0;
        data1="";
        while (cursor2.moveToNext()) {
            data1 = cursor2.getString(i);
            i++;
        }
        //Toast.makeText(DietActivity.this,"Dinner Gravy:- "+data1,Toast.LENGTH_SHORT).show();
        cursor2 = obj.displayCaloryData(data1);
        i = 0;
        data2 = "";
        while (cursor2.moveToNext()) {
            data2 = cursor2.getString(i);
            i++;
        }

        try{
            calory=Double.valueOf(data2);}
        catch (NumberFormatException e)
        { //       Toast.makeText(DietActivity.this,"NumberFormatException",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(DietActivity.this,"dinner gravy calory   "+calory,Toast.LENGTH_SHORT).show();


        gravy2 -= calory;
        //Toast.makeText(DietActivity.this, "remianing calories of dinner gravy first " +gravy2, Toast.LENGTH_SHORT).show();
        i=2;
        while(50d<gravy2 || gravy2>0d)
        {
            gravy2-=calory;

           // Toast.makeText(DietActivity.this, "amount is "+i+" of " + data1, Toast.LENGTH_SHORT).show();
            //Toast.makeText(DietActivity.this, "remianing calories of dinner gravy second " +gravy2, Toast.LENGTH_SHORT).show();
            i++;
        }
        tutorialList.add(new Tutorial(data1 ,data2+" calories"));
        obj.insertdiettable(UserId,data1 , data2,"Dinner",Day1);

        //Toast.makeText(DietActivity.this, "remianing calories of dinner gravy final " +gravy2, Toast.LENGTH_SHORT).show();



        //snacks data

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }
}
