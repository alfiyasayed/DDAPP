package com.example.dell.DDAPP;

        import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BMRActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener  {

     Button button_bmr ;
     EditText field_weight1 ;
    EditText field_name1 ;
     EditText field_height1 ;
     EditText field_age1 ;
    EditText field_contact1 ;
    EditText field_glucose1 ;

     RadioGroup radioGroup,Food_Type ;
     RadioButton radioButton1,radioButton2,Veg,NonVeg;

    float weight;
    float height;
    float age;
    float bmr;
    float bmi;
    String ans="";
    String ans1="";
    String[] spinner = { "Type 1 Diabetes", "Type 2 Diabetes", "Type 3 Diabetes"};
    String pos ;


     //Firebase databse
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference myRef;

    DatabaseHelper obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);
         obj = new DatabaseHelper(BMRActivity.this);


        button_bmr = findViewById(R.id.button_bmr);
        field_height1 = findViewById(R.id.height);
        field_weight1 = findViewById(R.id.weight);
        field_age1 = findViewById(R.id.age);
        field_height1 = findViewById(R.id.height);
        field_name1 =findViewById(R.id.name);
        field_glucose1 =findViewById(R.id.glucose);
        field_contact1 =findViewById(R.id.phone);

        radioButton1 =findViewById(R.id.radioButton1);
        radioButton2 =findViewById(R.id.radioButton2);
        radioGroup =findViewById(R.id.radiogroup);
        Veg =findViewById(R.id.Veg);
        NonVeg =findViewById(R.id.NonVeg);
       Food_Type =findViewById(R.id.Food_Type);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(BMRActivity.this);
        try{
            pos=spin.getSelectedItem().toString();}
        catch(Exception e){Toast.makeText(BMRActivity.this,""+e,Toast.LENGTH_LONG);}
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinner);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


         //Declare Database variables
       mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuthListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    //User Sign In
                    Toast.makeText(BMRActivity.this ,"Sucessful Sign in with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
                else{
                    //User Sign Out
                    Toast.makeText(BMRActivity.this ,"Sucessful Signout with "+user.getUid(),Toast.LENGTH_SHORT).show();
                }
            }

        };
       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                }

                // Log.d(TAG, "Value is: " + value);



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BMRActivity.this,""+databaseError.toException(),Toast.LENGTH_SHORT).show();
            }
        });*/

        button_bmr.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Nooooo",Toast.LENGTH_LONG);
                if(field_name1.getText().toString().equals("") || field_height1.getText().toString().equals("") || field_weight1.getText().toString().equals("") || field_age1.getText().toString().equals("") || field_contact1.getText().length()!=10|| field_contact1.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter all details",Toast.LENGTH_LONG);
                }else
                {
                    weight = Float.parseFloat(field_weight1.getText().toString());
                    height = Float.parseFloat(field_height1.getText().toString());
                    age = Float.parseFloat(field_age1.getText().toString());
                    int i=radioGroup.getCheckedRadioButtonId();
                    switch (i) {
                        case R.id.radioButton1:
                            bmr = (float) (66 + (13.7 * weight) + (5 * (height / 100)) - (6.8 * age));
                            ans=radioButton1.getText().toString();
                            break;
                        case R.id.radioButton2:
                            bmr = (float) (655 + (9.6 * weight) + (1.8 * (height / 100))- (4.7 * age));
                            ans=radioButton2.getText().toString();
                            break;
                    }
                    int i1=Food_Type.getCheckedRadioButtonId();
                    switch (i1) {
                        case R.id.Veg:
                           ans1=Veg.getText().toString();
                            break;
                        case R.id.NonVeg:
                            ans1=NonVeg.getText().toString();
                            break;
                    }
                    height=height/100;
                    height = height*height*height;
                    bmi=weight/height;
                   // Toast.makeText(BMRActivity.this, "" +bmr, Toast.LENGTH_LONG).show();
                   // Toast.makeText(BMRActivity.this, "" +bmi, Toast.LENGTH_LONG).show();
                    String weight1 = field_weight1.getText().toString();
                    String height1 = field_height1.getText().toString();
                    String age1 =field_age1.getText().toString();
                    String name =field_name1.getText().toString();
                    String phone =field_contact1.getText().toString();
                    String glucose =field_glucose1.getText().toString();
                    String bmr1= String.valueOf(bmr);
                    String bmi1= String.valueOf(bmi);

                    if(Float.valueOf(glucose)>Float.valueOf(6))
                {
                    Intent intent = new Intent(BMRActivity.this, GlucoseNotify.class);
                    startActivity(intent);


                }
                    FirebaseUser user = mAuth.getCurrentUser();
                    String UserId = user.getUid();
                    obj.creatediettable(UserId);
                    obj.insertwatertable(UserId,"","","");
                    obj.InsertUserData(UserId,name,weight1,height1,age1,ans,ans1,pos,glucose,phone,bmr1,bmi1);
                    startActivity(new Intent(BMRActivity.this,LoginActivity.class));

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
    private void setupUIViews()
    {
        button_bmr = (Button) findViewById(R.id.button_bmr);
        field_name1 =  findViewById(R.id.name);
        field_weight1 =  findViewById(R.id.weight);
        field_height1 =  findViewById(R.id.height);
        field_age1 =  findViewById(R.id.age);

        radioGroup = findViewById(R.id.radiogroup) ;
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
    }
    private Boolean validate()
    {
        Boolean result =false;
        String name = field_name1.getText().toString().trim();
        String weight = field_weight1.getText().toString().trim();
        String height = field_height1.getText().toString().trim();
        String age = field_age1.getText().toString().trim();
        if (name.isEmpty()  || weight.isEmpty() || height.isEmpty() || age.isEmpty()) {

            Toast.makeText(BMRActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }else
        {
            result = true;
        }return result;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //Toast.makeText(getApplicationContext(),spinner[position] ,Toast.LENGTH_LONG).show();
        pos=spinner[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
/*
@Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
}
@Override
    public void onStop(){
        super.onStop();
        if(mAuthListner!=null){
            mAuth.removeAuthStateListener(mAuthListner);
        }
}
*/

}
