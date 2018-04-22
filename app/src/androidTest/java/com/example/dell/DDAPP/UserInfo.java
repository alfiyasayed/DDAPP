package com.example.dell.DDAPP;

/**
 * Created by DELL on 28-01-2018.
 */

public class UserInfo {
   private String Name;
    private String Height;
    private String Weight;
    private String Gender;
    private String Age;
    private String BMR;
    private String BMI;
    private String Glucose;

    public UserInfo()
    {

    }
    public UserInfo(String Age,String BMI,String BMR,String Gender,String Glucose,String Height,String Name,String Weight)
    {
        this.Age=Age;
        this.BMI=BMI;
        this.BMR=BMR;
        this.Gender=Gender;
        this.Glucose=Glucose;
        this.Height=Height;
        this.Name=Name;
        this.Weight=Weight;





    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }
    public String getGlucose() {
        return Glucose;
    }

    public void setGlucose(String Glucose) {
        this.Glucose = Glucose;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getBMR() {
        return BMR;
    }

    public void setBMR(String BMR) {
        this.BMR = BMR;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String Height) {
        this.Height = Height;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight=Weight;
    }
}

