package com.example.dell.DDAPP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {
    private EditText userPassword2 , userPassword , userEmail;
    private Button btnSignUp,btnLogin1;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_signup11);
        setupUIViews();

        mAuth = FirebaseAuth.getInstance();

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    //insert Data
                    String user_email = userEmail.getText().toString().trim();
                    String user_psssword = userPassword.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(user_email, user_psssword)
                                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            // Sign in success, update UI with the signed-in user's information
                                           Toast.makeText(SignUpActivity.this,"SignUp Successful",Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(SignUpActivity.this,BMRActivity.class);
                                           // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(i);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            if(task.getException()instanceof FirebaseAuthUserCollisionException){Toast.makeText(SignUpActivity.this,"SignUp Failed",Toast.LENGTH_SHORT).show();}
                                            else{
                                            Toast.makeText(SignUpActivity.this,"SignUp Failed",Toast.LENGTH_SHORT).show();
                                        }}

                                        // ...
                                    }
                                });
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Enter Proper Data",Toast.LENGTH_SHORT).show();
                }

                }
            });


        }

    private void setupUIViews()
    {
        userPassword2 = (EditText)findViewById(R.id.etUserPassword2);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnLogin1 =(Button)findViewById(R.id.btnLogin1);

    }
    private Boolean validate()
    {
        Boolean result =false;
        String password = userPassword.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String password2 = userPassword2.getText().toString().trim();

        if (password.isEmpty()  || email.isEmpty() || password2.isEmpty()) {

            Toast.makeText(SignUpActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(password2)){
            Toast.makeText(SignUpActivity.this, "both password should b same", Toast.LENGTH_SHORT).show();
        }
       /*else if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(SignUpActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
        }*/
        else if(password.length()<6 || password2.length()<6)
        {
            Toast.makeText(SignUpActivity.this, "Minimum length of password should b 6", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }

}

