package com.example.dell.DDAPP;

/**
 * Created by DELL on 16-01-2018.
 */



        import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper {
    SQLiteDatabase db;
    public class SqHelper extends SQLiteOpenHelper
    {

        public SqHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String s = "Create table Userdata ( _id integer primary key autoincrement,UserId text, Username text ,Userweight text ,Userheight text , Userage text ,Usergender text,UserFood text,UserDiabetesType text,Userglucose text,Docphone text, Userbmr text,Userbmi text)";
            sqLiteDatabase.execSQL(s);
            String s2="Create table Food_type(_id integer primary key autoincrement,food_type text )";
            sqLiteDatabase.execSQL(s2);
            String s1 = "Create table Food ( _id integer primary key autoincrement,food_item text,proteins text ,fats text  ,carb text  ,calory text ,sugar text,category text,Foodtype_id integer ,FOREIGN KEY(Foodtype_id) REFERENCES Food_type(Food_type_id))";
            sqLiteDatabase.execSQL(s1);
            String s3 = "Create table waterintake( _id integer primary key autoincrement,UserId  text,water text,glass text,Date text)";
            sqLiteDatabase.execSQL(s3);
           /* String s3 = "Create table Todays_diet( _id integer primary key autoincrement,food_item text,calory text)";
            sqLiteDatabase.execSQL(s3);*/

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

           
        }
    }
    public void InsertTodays_Diet_Data(String food_item , String calory)
    {
        db.execSQL("insert into Todays_diet( food_item ,calory ) values ('"+food_item+"','"+calory+"')");
    }

    public DatabaseHelper(Context context)
    {
        SqHelper obj = new SqHelper(context,"MyDB_UserData",null,1);
        db = obj.getWritableDatabase();
    }

    public void InsertUserData(String userid , String name,String weight,String height,String age,String gender,String food ,String diabetes,String glucose,String contact,String bmr,String bmi)
    {
        db.execSQL("insert into Userdata(UserId , Username , Userweight ,Userheight , Userage ,Usergender ,UserFood,UserDiabetesType,Userglucose,Docphone, Userbmr ,Userbmi ) values ('"+userid+"','"+name+"','"+weight+"','"+height+"','"+age+"','"+gender+"','"+food+"','"+diabetes+"','"+glucose+"','"+contact+"','"+bmr+"','"+bmi+"')");
    }

    public void UpdateUserData(String userid , String name,String weight,String height,String age,String gender,String glucose,String contact,String bmr,String bmi)
    {
        db.execSQL("update Userdata set UserId='"+userid+"',Username='"+name+"',Userweight='"+weight+"',Userheight='"+height+"',Userage='"+age+"',Usergender='"+gender+"' ,Userglucose='"+glucose+"',Docphone='"+contact+"',Userbmr='"+bmr+"',Userbmi='"+bmi+"'");
    }
    public Cursor SelectUserData()
    {
        return db.rawQuery("Select * from Userdata",null);
    }

public Cursor SearchUserData(String name){
       return db.rawQuery("Select Userpassword from Userdata where Username = '" +name + "'",null);}

    public Cursor SearchGlucose(String userid){
        return db.rawQuery("Select Userglucose from Userdata where UserId = '" +userid + "'",null);}

       public Cursor SearchSignUpUserData(String name)
{
    return db.rawQuery("Select Username from Userdata where Username = '" +name + "'",null);}

    public void insertbmrdata(String name,String weight,String height,String age,String bmr,String bmi)
    {
        db.execSQL("update Userdata set Userweight='"+weight+"',Userheight='"+height+"',Userage='"+age+"',Userbmr='"+bmr+"',Userbmi='"+bmi+"'where Username = '"+name+"';");
          }
    public Cursor searchweight(String UserId)
    {

        return db.rawQuery("Select Userweight from Userdata where UserId = '" +UserId + "'",null);
    }

    public Cursor searchcontact(String UserId)
    {

        return db.rawQuery("Select Docphone from Userdata where UserId = '" +UserId + "'",null);
    }
    public Cursor searchbmr(String UserId)
    {
        return db.rawQuery("Select Userbmr from Userdata where UserId = '" +UserId + "'",null);
    }
    public Cursor searchgender(String UserId)
    {
        return db.rawQuery("Select Usergender from Userdata where UserId = '" +UserId + "'",null);
    }
    public Cursor searchUserFoodType(String UserId)
    {
        return db.rawQuery("Select UserFood from Userdata where UserId = '" +UserId + "'",null);
    }
    public Cursor retriveFoodId(String UserId)
    {
        return db.rawQuery("Select _id from Userdata where UserId = '" +UserId + "'",null);
    }
    //insert food data
    public void insertFood_typedata()
    {
        db.execSQL("insert into Food_type(food_type) values "+"('Gravy')");
        db.execSQL("insert into Food_type(food_type) values "+"('Roti')");
        db.execSQL("insert into Food_type(food_type) values "+"('Dry')");
        db.execSQL("insert into Food_type(food_type) values "+"('Rice')");
        db.execSQL("insert into Food_type(food_type) values "+"('PreBreakFast')");
        db.execSQL("insert into Food_type(food_type) values "+"('BreakFast')");
        db.execSQL("insert into Food_type(food_type) values "+"('Soup')");


    }
    public Cursor getFood_type()    {

        return db.rawQuery("Select * from Food_type",null);
    }
    public void chkFoodTable()
    {
        db.execSQL("DROP table if exists Food");
        db.execSQL("drop table if exists Food_type ");
        String s1 = "Create table Food ( _id integer primary key autoincrement,food_item text,proteins text ,fats text  ,carb text  ,calory text ,sugar text,category text,Foodtype_id integer ,FOREIGN KEY(Foodtype_id) REFERENCES Food_type(Food_type_id))";
        db.execSQL(s1);
        String s2="Create table Food_type(_id integer primary key autoincrement,food_type text )";
        db.execSQL(s2);
           }
    public void insertfooddata()
    {

        // db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ) values ('dal','14','6','21','170','3.5')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values "+"('White rice,medium grain(0.8 cup)','4.43','0.39','53.18','100','0.1','veg','4')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Coconut chicken curry(1 serving)','8.4','26','16','320','0.2','nonveg','1')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Chapati with oil','14','20','5','200','0','veg','2')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Chapati without oil (Roti)','0','16','3','90','0','veg','2')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('yellow rice, large grain(1 cup)','4.25','0.44','44.51','150','0.1','veg','4')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Palak paneer(1/2 cup)','8','12','9','190','0.5','veg','1')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id)values ('aloo mutter(1 cup cooked)','6','7','40','240','0','veg','3')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Dal khichdi,1 cup','20','6.8','60.8','277','0','veg','4')");
       db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Mutter paneer','5','10','11','190','1','veg','1')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Paneer malai','6.1','20.1','7.8','237','2','veg','1')");
      db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Paneer darbari','8','27','11','120','0.5','veg','1')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Palak chole','8.6','12.4','22','181','0','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Fried fish nuggets (catfish)','16','12','7','199','0','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Vegetable chettinad','2','8','7','110','0','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Paneer chettinad','20','27','22','415','6','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Fried cod fish','25','21','29','395','0','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Mutton curry','13','17','20','271','0','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id )values ('Mutton biryani','24','10','51','387','0','nonveg',4)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Mutton roast','33','0.1','11','234','','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Mutton pulao','16','11','29','287','0','nonveg',4)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Mutton sausage','16','0','8.4','100','0','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Mutton biryani','24','10','51','387','0','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Chicken tandoori','19','6','36','270','3','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Chicken 65','13.4','3.8','6','110','4','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Butter chicken','33','12','10','314','5','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Chicken makhani','25','25','6','350','2','nonveg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Paneer makhani','7.5','22.5','13.5','287','3','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Vegetable makhani','5.8','16','27.1','258','','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Dal makhani','5','9','11','150','2','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Dal (0.5cup)','7','1','3','110','0','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Palak (1 cup)','18','1','40','212','1.2','veg',1)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id )values ('coconut chicken curry','25.12','22.82','28.04','335','2.2','nonveg',1)");
        //db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Veg hakka noodles (1 serving)','11','13','54','370','2','veg',)");
        //db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Chicken Macroni (1 serving)','24','11','42','310','10','nonveg')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Phulka(2 servings)','4','3','24','140','0','veg',2)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Vegetable Raita','3','27','5','58','0','veg',6)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Curd','6','8','1','100','0','veg',5)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Palak paneer,1/2 cup','6','12','9','185','2.5','veg',1)");
        //db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('aloo mutter, 1/2 pack serving','4','7','19','200','3','veg',)");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Khichdi,1 cup','6.12','1.78','34.28','150','3','veg',4)");
         db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Chicken tandoori','9','6','36','270','3','nonveg',1)");


       //PreBreakFast Data Insert
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('6 Almonds','1.8','3.6','0.6','37.8','0','veg','5')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('fenugreek seeds 1 table spoon soaked in water for overnight ','2.6','0.7','6.5','35.9','0','veg','5')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Psyllium Husk 1 table spoon pawder in 1 glass of water ','0','0','8','34','0','veg','5')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('1 Apple','1.8','3.6','0.6','37.8','0','veg','5')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Buttermilk (Chaach)','8','2','12','98','12','veg','5')");

//BreakFastData
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Dosa','1','21','3','100','0.1','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category, Foodtype_id) values ('Paratha homemade','7','30','3','180','0.2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('1 egg omlet with onions,tomato,bell pepper','11','25','9','295','0','nonveg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('bread(white)','1','52','8','245','2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Wheat, bread(brown)','1','49','9','244','0.5','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Aalo paratha, homemade','4.8','25.9','3.4','214','5','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Boiled egg','3','3','0','35','0','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Banana (1 medium sized)','1','0','23','89','0.7','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Sandwich (2 pieces of bread)','37','23','91','315','2.2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Apple (1 medium sized)','0','0','17','65','14.5','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Upma (1 serving)','4','2','32','180','2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Sabudana wada (5 pieces)','2','7','6.7','176','0','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Orange (1 medium sized)','1.7','0','22','87','12.2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Veg Macroni (1 serving)','7','1','42','210','0','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Eggs, scrambled, made with 2 eggs','13','14','4','189','4','nonveg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Whole wheat pasta','8','2','39','180','2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Orange juice, ready-to-drink','1','0','27','110','23','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('French toast(1 piece)','10.8','3.6','15.5','175','5.2','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Potato pancake(1 each)','1','2','4','150','0','veg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Egg white burji(3 egg)','14','5.6','20.5','179','3.1','nonveg','6')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Veg Poha','9.5','1.5','38.2','270','0','veg','6')");

        //Soup Data
       db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category,Foodtype_id ) values ('Mutton soup','27','14','10','274','10','nonveg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Tomato basil soup','5.8','6.6','21.5','154','15','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Veg manchow soup','0','3.7','69.7','328','0','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Carrot and orange soup','2','1','17','80','7','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Mushroom soup','4.8','11','4','90','1','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Broccolli soup','3','1.5','14','80','0','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Tomato basil soup','5.8','6.6','21.5','154','15','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Veg manchow soup','0','3.7','69.7','328','0','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Carrot and orange soup','2','1','17','80','7','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Mushroom soup','4.8','11','4','90','1','veg','7')");
        db.execSQL("insert into Food( food_item ,proteins ,fats  ,carb  ,calory  ,sugar ,category ,Foodtype_id) values ('Chicken noodle soup','6','9.8','3.9','99','1','nonveg','7')");


    }
    //retrive data for lunch
    public void retriveRicedata(double calory,String foodtype ) {
        db.execSQL("DROP table if exists caloriedata");
        double range1 = calory - 10d;
        double range2 = calory + 10d;
        String from1 = Double.toString(range1);
        String to1 = Double.toString(range2);
        if (foodtype == "veg") {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where calory <=" + to1 + " and category IN 'veg' and Foodtype_id IN (Select _id from Food_type where food_type='Rice') ");
        }
        else {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where calory <="+to1+" and Foodtype_id IN (Select _id from Food_type where food_type='Rice') ");

        }
    }
    public void retriveRotidata(double calory,String foodtype) {
        db.execSQL("DROP table if exists caloriedata");
        double range1=calory-10d;
        double range2=calory+10d;
        String from1=Double.toString(range1);
        String to1=Double.toString(range2);
        if (foodtype == "veg") {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where calory <=" + to1 + "and category='veg' and Foodtype_id IN (Select _id from Food_type where food_type='Roti') ");
        }
        else {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where calory <=" + to1 + " and Foodtype_id IN (Select _id from Food_type where food_type='Roti') ");
        }    }
    public void retriveGravydata(double calory,String foodtype) {
        db.execSQL("DROP table if exists caloriedata");
        double range1=calory-10d;
        double range2=calory+10d;
        String from1=Double.toString(range1);
        String to1=Double.toString(range2);
        if (foodtype == "veg") {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where calory <=" + to1 + "and category='veg' and Foodtype_id IN (Select _id from Food_type where food_type='Gravy') ");
        }else {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where calory <=" + to1 + " and Foodtype_id IN (Select _id from Food_type where food_type='Gravy') ");
        }    }
    public void retrivePreBreakFastdata(String foodtype)
    {
        db.execSQL("DROP table if exists caloriedata");
        if(foodtype=="veg"){
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where category='veg' and Foodtype_id IN (Select _id from Food_type where food_type='PreBreakFast') ");}
else{
        db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where Foodtype_id IN (Select _id from Food_type where food_type='PreBreakFast') ");
    }}
    public void retriveBreakFastdata(String foodtype)
    {
        db.execSQL("DROP table if exists caloriedata");
        if(foodtype=="veg") {
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where category='veg' and Foodtype_id IN (Select _id from Food_type where food_type='BreakFast') ");
        }else{
            db.execSQL("Create table caloriedata as Select _id,food_item,calory,sugar from Food where Foodtype_id IN (Select _id from Food_type where food_type='BreakFast') ");

        }
    }

    public Cursor displayFoodData()
    {
        return db.rawQuery("Select food_item from caloriedata where sugar between 0 and 1 order by random() limit 1" ,null);

    }
    public Cursor displayCaloryData(String data)
    {
        return db.rawQuery("Select calory from caloriedata where food_item ='"+data+"'" ,null);

    }
        //Diet Table for each user

    public Cursor searchUserDate(String UID,int i)
    {
        return db.rawQuery("Select Date from "+UID+" where _id = '" +i + "'",null);
    }
    public Cursor countdietrow(String UID)
    {
        return db.rawQuery("Select COUNT(*) from " + UID + "",null);
    }
    public Cursor searchDietFood(String UID,int k)
    {
        return db.rawQuery("Select food_item from "+UID+" where  _id = '" +k + "'",null);    }
    public Cursor searchDietCalory(String UID,int k)
    {
        return db.rawQuery("Select calory from "+UID+" where _id = '" +k + "'",null);    }

    public void creatediettable(String UID) {
        String s3 = "Create table " + UID + "( _id integer primary key autoincrement,food_item text,calory text,Food_Type text,Date text)";
        db.execSQL(s3);
      /*  String s4 = "insert into " + UID + "( food_item ,calory ,Date,Food_Type ) values ('gh','20','12','gh')";
        db.execSQL(s4);*/
    }
    public void insertdiettable(String UID,String food,String calory,String Food_Type,String Date) {
        String s4 = "insert into " + UID + "( food_item ,calory ,Food_Type,Date ) values ('"+food+"','"+calory+"','"+Food_Type+"','"+Date+"')";
        db.execSQL(s4);
    }
    public Cursor countdietPrerow(String UID,String Food_Type)
    {
        return db.rawQuery("Select COUNT(*) from " + UID + " where Food_Type ='"+Food_Type+"'",null);
    }
    public Cursor countdietBrerow(String UID,String Food_Type)
    {
        return db.rawQuery("Select COUNT(*) from " + UID + " where Food_Type ='"+Food_Type+"'",null);
    }
    public Cursor countdietLunchrow(String UID,String Food_Type)
    {
        return db.rawQuery("Select COUNT(*) from " + UID + " where Food_Type ='"+Food_Type+"'",null);
    }
    public Cursor countdietDinnerrow(String UID,String Food_Type)
    {
        return db.rawQuery("Select COUNT(*) from " + UID + " where Food_Type ='"+Food_Type+"'",null);
    }
    public void deleteDietRow(String UID)
    {
        db.execSQL("DELETE FROM "+UID+"");

    }

    //Table for WaterIntake
    public void insertwatertable(String UID,String water,String glass,String Date) {


        db.execSQL("insert into waterintake( UserId,water,glass,Date) values ('"+UID+"','"+water+"','"+glass+"','"+Date+"')");

    }
      public void updatewater(String UID,String water,String glass,String Date)
      {
          String s3 = "Update waterintake SET water='"+water+"',glass='"+glass+"',Date='"+Date+"' where UserId ='"+UID+"'";
          db.execSQL(s3);
      }
    public Cursor searchwater(String UID)
    {
        return db.rawQuery("SELECT water from waterintake where UserId ='"+UID+"'",null);
    }
    public Cursor searchglass(String UID)
    {
        return db.rawQuery("SELECT glass from waterintake where UserId ='"+UID+"'",null);
    }
    public Cursor searchwaterDate(String UID)
    {
        return db.rawQuery("SELECT Date from waterintake where UserId ='"+UID+"'",null);
    }
}



