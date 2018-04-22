package com.example.dell.DDAPP;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by ALFIYA on 02/03/2018.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Get a Calendar instance
        final Calendar calendar = Calendar.getInstance();
        // Get the current hour and minute
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        /*
            Creates a new time picker dialog.
                TimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener listener,
                    int hourOfDay, int minute, boolean is24HourView)
         */
        // Create a TimePickerDialog with current time
        TimePickerDialog tpd = new TimePickerDialog(getActivity(),this,hour,minute,false);
        // Return the TimePickerDialog
        return tpd;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute){



        // Do something with the returned time
        TextView tv1 = (TextView) getActivity().findViewById(R.id.tv1);

        TextView tv2 = (TextView) getActivity().findViewById(R.id.tv2);

        tv1.setText(""+hourOfDay+":" );
        tv2.setText(""+minute );
    }

}