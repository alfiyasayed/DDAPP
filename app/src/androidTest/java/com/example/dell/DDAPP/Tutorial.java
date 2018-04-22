package com.example.dell.DDAPP;
import java.util.Arrays;
/**
 * Created by DELL on 30-03-2018.
 */

public class Tutorial {
    private String Meal;
    private String Food;
    private String Calory;
    private Double Sugar;

    public Tutorial(/*String Meal,*/String Food,String Calory){
        this.Meal=Meal;

        this.Food = Food;

      this.Calory= Calory;
        this.Sugar=Sugar;
    }

    public String getMeal() {
        return Meal;
    }

    public void setMeal(String meal) {
        Meal = meal;
    }

    public String getFood() {
        return Food;

    }

    public void setFood(String food) {

            Food = food;

    }

    public String getCalory() {

        return Calory;
    }

    public void setCalory(String calory) {

            Calory =  calory;


    }

    public Double getSugar() {
        return Sugar;
    }

    public void setSugar(Double sugar) {
        Sugar = sugar;
    }
}
