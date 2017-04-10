package com.example.pc_100.myapplication.todolist.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sjjkk on 2017/4/10.
 */

public class TaskDbHelper extends SQLiteOpenHelper {
    Context mContext;
    private static final String TABLE_NAME = 
    TaskDbHelper(Context context){
       super(context,);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
