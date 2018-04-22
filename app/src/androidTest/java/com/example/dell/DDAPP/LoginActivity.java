package com.example.dell.DDAPP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView SignUp;
    private Button Login;
    private int counter =5;
    private TextView userRegistration;


    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference myRef;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login11);

        mAuth = FirebaseAuth.getInstance();

       Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        SignUp = (TextView)findViewById(R.id.tvSignUp);
        Login = (Button)findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {

                    final String user_email = Email.getText().toString().trim();
                    String user_psssword = Password.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(user_email,user_psssword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {   //*****************************************************************
                                SaveSharedPreference.setUserName(LoginActivity.this ,user_email);



                                Toast.makeText(LoginActivity.this ,"Successful Login",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,Navigation_WaterIntakeActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                               startActivity(i);
                            }
                            else
                            {
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                        });
                    }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

    }
    private Boolean validate()
    {
        Boolean result =false;
      //  String name = userName.getText().toString();
        String password = Password.getText().toString();
        String email = Email.getText().toString();
        if ( password.isEmpty()  && email.isEmpty() ) {

            Toast.makeText(LoginActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
      /*  else if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(SignUpActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }*/
        else if(password.length()<6)
        {
            Toast.makeText(LoginActivity.this, "Minimum length of password should b 6", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }

}
