package com.example.dell.DDAPP;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 16-02-2018.
 */

public class DietDatabase extends DatabaseHelper {
    SQLiteDatabase db;

    public DietDatabase(Context context) {
        super(context);
    }

}

