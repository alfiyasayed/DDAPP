package com.example.dell.DDAPP;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ALFIYA on 17/03/2018.
 */

public class Dietplan {


    SQLiteDatabase db1;
    String food,exist,roti,gravy;




    public class SHelper extends SQLiteOpenHelper {

        public SHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String drop1="drop table if exists food";
            sqLiteDatabase.execSQL(drop1);

            String drop2="drop table if exists roti";
            sqLiteDatabase.execSQL(drop2);

            String drop3="drop table if exists gravy";
            sqLiteDatabase.execSQL(drop3);

            String ss = "Create table food(_id integer primary key autoincrement,foodname text,calorie text,sugar text,carbs text,type text)";
            sqLiteDatabase.execSQL(ss);

            String s = "Create table roti(_id integer primary key autoincrement,foodname text,calorie text,sugar text,carbs text,type text)";
            sqLiteDatabase.execSQL(s);

            String sss = "Create table gravy(_id integer primary key autoincrement,foodname text,calorie text,sugar text,carbs text,type text)";
            sqLiteDatabase.execSQL(sss);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            db1.execSQL("drop table if exists food");
            onCreate(db1);
            db1.execSQL("drop table if exists dietdata");
            onCreate(db1);
        }
    }

    public Dietplan(Context context) {
        SHelper obj = new SHelper(context, "MyfoodDB", null, 1);
        db1 = obj.getWritableDatabase();


        db1.execSQL("insert into food(foodname,calorie,sugar,carbs,type)values('roti bajra','105','0.0','20.0','gravy')");
        db1.execSQL("insert into food(foodname,calorie,sugar,carbs,type)values('roti jawar','110','0.0','0.0','gravy')");
        db1.execSQL("insert into food(foodname,calorie,sugar,carbs,type)values('roti wheat','130','2.0','26.0','roti')");

        //db1.execSQL("insert into food(foodname,calorie,sugar,carbs,type)values('roti4','300','0.6','roti')");

        db1.execSQL("insert into roti(foodname,calorie,sugar,carbs,type)values('roti bajra','105','0.0','20.0','roti')");
        db1.execSQL("insert into roti(foodname,calorie,sugar,carbs,type)values('roti jawar','110','0.0','0.0','roti')");
        db1.execSQL("insert into roti(foodname,calorie,sugar,carbs,type)values('roti wheat','130','2.0','26.0','roti')");


        db1.execSQL("insert into gravy(foodname,calorie,sugar,carbs,type)values('roti bajra','105','0.0','20.0','gravy')");
        db1.execSQL("insert into gravy(foodname,calorie,sugar,carbs,type)values('roti jawar','110','0.0','0.0','gravy')");
        db1.execSQL("insert into gravy(foodname,calorie,sugar,carbs,type)values('roti wheat','130','2.0','26.0','roti')");

     //   db1.execSQL("Create table dietdata as Select _id,foodname,calorie,sugar from food where sugar between 0.2 and 0.4 AND calorie between 100 and 202");

     //   db1.execSQL("Create table data(_id integer primary key autoincrement,foodname text,type text,Date date default current_date)");
      //  db1.execSQL("insert into data(foodname)values('roti4')");

    }


    //  db1.execSQL("Create table caloriedata as Select _id,foodname,calorie,sugar from food where ");


    public void savedata(String foodname,String type)
    {
        db1.execSQL("insert into data(foodname,type)values('"+foodname+"','"+type+"')");
    }





    public void dietdata(Context context)

    {

        try {
           Cursor c= db1.rawQuery("select foodname from dietdata order by random() limit 1",null);



            int i = 0;

            while (c.moveToNext()) {
                food = c.getString(i);
                i++;
            }




            Cursor exists = db1.rawQuery("select EXISTS(Select * from data where foodname=" + food + ")",null);

            int ii = 0;

            while (exists.moveToNext()) {
                exist = exists.getString(i);
                i++;
            }
           /* if(exists!=null) {

                dietdata(context);
            }

            else {

                Toast.makeText(context.getApplicationContext(), "not exist", Toast.LENGTH_SHORT).show();
                db1.execSQL("insert into data(foodname,date)values(food)");

            }*/

/*
            int exist = Integer.parseInt(exists);
            if (exist == 1) {
                s = "select foodname from dietdata orderby limit=1";
                exists = "select EXISTS(Select * from data where foodname=" + s + ")";
                Toast.makeText(context.getApplicationContext(), "Hello" + exists, Toast.LENGTH_SHORT).show();

                //db1.execSQL("insert into data(foodname,date)values(food)");
*/
            }



        //  db1.execSQL("Create table randomm as select * from food order by random() limit 1");

        catch (Exception e) {

            //Toast.makeText(context.getApplicationContext(), "ERROR"+e , Toast.LENGTH_SHORT).show();

        }

    }


    public void clearData() {
        db1.execSQL("drop table if exists caloriedata");
        db1.execSQL("drop table if exists dietdata");
    }

public Cursor Selectroti()
{
roti=roti;
   return db1.rawQuery("select foodname from roti order by random() limit 1",null);

}

    public Cursor Selectgravy()
    {
        gravy=gravy;
        return db1.rawQuery("select foodname from food where carbs<21.0 and calorie<110 order by random() limit 1",null);

    }

    public Cursor Getdata(String data)
    {
        return db1.rawQuery("select calorie from food where foodname='"+data+"'", null);

    }

    public Cursor Selectdietdata() {
        return db1.rawQuery("select foodname from dietdata order by random() limit 1", null);

    }


    public Cursor existdata(String food) {
        //String food="roti4";
        return db1.rawQuery("Select * from data where foodname='"+food+"'",null);

    }


    public Cursor SelectUserData() {
        return db1.rawQuery("Select * from UserData", null);
    }


}



