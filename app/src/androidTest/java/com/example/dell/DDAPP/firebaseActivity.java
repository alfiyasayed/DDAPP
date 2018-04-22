package com.example.dell.DDAPP;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class firebaseActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;
    private Firebase fb;
    public FirebaseDatabase mFirebaseDatabase;
    public FirebaseAuth.AuthStateListener mAuthListner;
    public DatabaseReference myRef;
    public String UserId;



    public String name1, age1, bmr1, weight1, height1, bmi1, gender1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);





               FirebaseAuth mAuth = FirebaseAuth.getInstance();
                // Toast.makeText(Navigation_WaterIntakeActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                mFirebaseDatabase = FirebaseDatabase.getInstance();
                myRef = mFirebaseDatabase.getReference();
                FirebaseUser user = mAuth.getCurrentUser();
                //FirebaseUser user = firebaseAuth.getCurrentUser();
                UserId = user.getUid();


                mAuthListner = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(firebaseActivity.this, "Successful Sign in with " + user.getUid(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(firebaseActivity.this, "Successful Signout", Toast.LENGTH_SHORT).show();
                        }
                    }

                };

    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

           // Toast.makeText(firebaseActivity.this, "" + UserId, Toast.LENGTH_SHORT).show();
if(dataSnapshot.exists()) {
    for (DataSnapshot ds : dataSnapshot.getChildren()) {
        try {

            UserInfo uInfo = new UserInfo();
            uInfo.setName(ds.child(UserId).getValue(UserInfo.class).getName());
            uInfo.setAge(ds.child(UserId).getValue(UserInfo.class).getAge());
            uInfo.setBMI(ds.child(UserId).getValue(UserInfo.class).getBMI());
            uInfo.setBMR(ds.child(UserId).getValue(UserInfo.class).getBMR());
            uInfo.setWeight(ds.child(UserId).getValue(UserInfo.class).getWeight());
            uInfo.setHeight(ds.child(UserId).getValue(UserInfo.class).getHeight());
            uInfo.setGender(ds.child(UserId).getValue(UserInfo.class).getGender());
               /* ds.child(UserId).getValue(UserInfo.class).setName(uInfo.getName());
                ds.child(UserId).getValue(UserInfo.class).setAge(uInfo.getAge());
                ds.child(UserId).getValue(UserInfo.class).setBMI(uInfo.getBMI());
                ds.child(UserId).getValue(UserInfo.class).setBMR(uInfo.getBMR());
                ds.child(UserId).getValue(UserInfo.class).setWeight(uInfo.getWeight());
                ds.child(UserId).getValue(UserInfo.class).setHeight(uInfo.getHeight());
                ds.child(UserId).getValue(UserInfo.class).setGender(uInfo.getGender());*/
            name1 = uInfo.getName();
            age1 = uInfo.getAge();
            bmi1 = uInfo.getBMI();
            bmr1 = uInfo.getBMR();
            weight1 = uInfo.getWeight();
            height1 = uInfo.getHeight();
            gender1 = uInfo.getGender();

            SaveSharedPreference.setUserAge(firebaseActivity.this, age1);
            SaveSharedPreference.setUserBmi(firebaseActivity.this, bmi1);
            SaveSharedPreference.setUserBmr(firebaseActivity.this, bmr1);
            SaveSharedPreference.setUserWeight(firebaseActivity.this, weight1);
            SaveSharedPreference.setUserHeight(firebaseActivity.this, height1);
            SaveSharedPreference.setUserGender(firebaseActivity.this, gender1);

        } catch (Exception e) {
           // Toast.makeText(firebaseActivity.this, "error" + e, Toast.LENGTH_SHORT).show();

        }


    }
}



        }


        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });



    }
}
