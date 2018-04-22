package com.example.dell.DDAPP;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class AutoLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_login);
       int SPLASH_TIME_OUT =500;

        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {
                  if(SaveSharedPreference.getUserName(AutoLogin.this).length() == 0)
                {
                    Intent i=new Intent(AutoLogin.this,LoginActivity.class);
                    startActivity(i);

                }
                else
                {


                    //**********************************************************************************************************
                    Intent i=new Intent(AutoLogin.this,Navigation_WaterIntakeActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }





    }

