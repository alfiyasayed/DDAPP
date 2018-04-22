package com.example.dell.DDAPP;

/**
 * Created by DELL on 30-03-2018.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;


import java.util.*;

public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.MyViewHolder> {

    private List<Tutorial>tutorialList;
    public TutorialsAdapter (List<Tutorial>tutorialList){
        this.tutorialList=tutorialList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView Meal,Food,Calory;
        public ListView listView;

        public MyViewHolder(View view)
        {
            super(view);
            //Meal=(TextView) view.findViewById(R.id.MealText);
            Food=(TextView) view.findViewById(R.id.FoodText);
            Calory=(TextView) view.findViewById(R.id.CaloryText);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tutorial_list_row,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
Tutorial tutorial=tutorialList.get(position);
//holder.Meal.setText(tutorial.getMeal());
        holder.Food.setText(tutorial.getFood());

        holder.Calory.setText(tutorial.getCalory());

    }

    @Override
    public int getItemCount() {
        return tutorialList.size();
    }
}
