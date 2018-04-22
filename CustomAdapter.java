package com.example.dell.DDAPP;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ALFIYA on 03/03/2018.
 */

public class CustomAdapter extends ArrayAdapter {
    String[] numbers ;
    public CustomAdapter(@NonNull Context context, int resource, int textViewResourceId, String[] objects, String[] numbers) {
        super(context, resource, textViewResourceId, objects);
        this.numbers = numbers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        TextView tv = v.findViewById(R.id.tv1);
        TextView tv2 = v.findViewById(R.id.tv2);
        //if(tv.getText().equals("a")){
         LinearLayout ll = v.findViewById(R.id.Linear);
        // ll.setBackgroundColor(Color.RED);
        // }else{

        //LinearLayout ll = v.findViewById(R.id.linearLayout);
        //ll.setBackgroundColor(Color.GREEN);
        // }
        tv2.setText(numbers[position]);


        return v;
    }
}