package com.example.pc_100.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ShenJack on 2017/4/9.
 */

public class MainDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "main.db";
    private static final int DATABASE_VERSION = 1;


    public MainDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MAIN_TABLE = "CREATE TABLE "+ MainContract.MainEntry.TABLE_NAME
                + "(" +
                MainContract.MainEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                MainContract.MainEntry.COLUMN_MODULE_NAME + " TEXT NOT NULL, " +
                MainContract.MainEntry.COLUMN_MODULE_DESCRIPTION + "TEXT NOT NULL "+");";

        db.execSQL(SQL_CREATE_MAIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MainContract.MainEntry.TABLE_NAME);
        onCreate(db);
    }
}
