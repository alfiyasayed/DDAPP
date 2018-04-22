package com.example.dell.DDAPP;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ALFIYA on 14/03/2018.
 */
public class SaveSharedPreference
{
    static final String PREF_USER_NAME= "username";
    static final String pref_user_age="userage";
    static final String pref_user_weight="userweight";
    static final String pref_user_height="userheight";
    static final String pref_user_bmr="userbmr";
    static final String pref_user_bmi="userbmi";
    static  final String pref_user_gender="usergender";
    static final String pref_user_glass = "userglass";
    static final String pref_user_water = "";

    static final String pref_defined_glass = "";
    static final String pref_defined_water = "";
    static final String pref_Uid="";
    static final String pref_var="0";







    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }
    public static void setUserAge(Context ctx, String userAge)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_age, userAge);
        editor.commit();
    }
    public static void setUserWeight(Context ctx, String userWeight)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_weight, userWeight);
        editor.commit();
    }
    public static void setUserHeight(Context ctx, String userHeight)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_height, userHeight);
        editor.commit();
    }
    public static void setUserBmr(Context ctx, String userBmr)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_bmr, userBmr);
        editor.commit();
    }
    public static void setUserBmi(Context ctx, String userBmi)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_bmi, userBmi);
        editor.commit();
    }
    public static void setUserGender(Context ctx, String userGender)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_gender, userGender);
        editor.commit();
    }
    public static void setUserGlass(Context ctx, String userGlass)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_glass,userGlass);
        editor.commit();
    }
    public static void setUserWater(Context ctx, String userWater)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_user_water,userWater);
        editor.commit();
    }




    public static void setDefinedglass(Context ctx, String definedGlass)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_defined_glass,definedGlass);
        editor.commit();
    }
    public static void setDefinedWater(Context ctx, String definedWater)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_defined_water,definedWater);
        editor.commit();
    }

    public static void setUid(Context ctx, String Uid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(pref_Uid,Uid);
        editor.commit();
    }
    public static void setVar(Context ctx, Integer var)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(pref_var,var);
        editor.commit();
    }


    public static Integer getVar(Context ctx)
    {
        return getSharedPreferences(ctx).getInt(pref_var,0);
    }

    public static String getUid(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_Uid, "");
    }
    public static String getDefinedglass(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_defined_glass, "");
    }
    public static String getDefinedWater(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_defined_water, "");
    }
    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    public static String getUserWeight(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_weight, "");
    }
    public static String getUserHeight(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_height, "");
    }
    public static String getUserBmi(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_bmi, "");
    }
    public static String getUserBmr(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_bmr, "");
    }
    public static String getUserAge(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_age, "");
    }
    public static String getUserGender(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_gender, "");
    }

    public static String getUserGlass(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_glass, "");
    }
    public static String getUserWater(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_user_water, "");
    }
    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
