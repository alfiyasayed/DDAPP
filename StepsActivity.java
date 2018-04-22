
package com.example.dell.DDAPP;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


    public class StepsActivity extends AppCompatActivity implements SensorEventListener {
        TextView tv_steps;

        SensorManager sensormanager;
        boolean running=false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_steps);

            tv_steps =(TextView) findViewById(R.id.tv_steps);
            sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        }

        @Override
        protected void onResume() {
            super.onResume();
            running=true;
            Sensor countsensor= sensormanager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if(countsensor!=null)
            {
                sensormanager.registerListener(this,countsensor,SensorManager.SENSOR_DELAY_UI);
            }
            else
            {
                Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPause() {
            super.onPause();
            running=false;
            //sensormanager.unregisterListener(this,);
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(running)
            {
                tv_steps.setText(String.valueOf(sensorEvent.values[0]));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

