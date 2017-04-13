package com.example.pc_100.myapplication.todolist.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.pc_100.myapplication.todolist.Data.TaskContract.TaskEntry.TABLE_NAME;


/**
 * Created by sjjkk on 2017/4/10.
 */

public class TaskDbHelper extends SQLiteOpenHelper {
    Context mContext;
    private static final String DATABASE_NAME = "todolist.db";
    private static final int VERSION = 1;
    TaskDbHelper(Context context){
       super(context,TABLE_NAME,null,VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                TaskContract.TaskEntry._ID         +" INTEGER PRIMARY KEY, " +
                TaskContract.TaskEntry.COLOMN_DESCRIPTION + " TEXT NOT NULL,"+
                TaskContract.TaskEntry.COLOMN_PRIORITY + " INTEGER NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }
}
