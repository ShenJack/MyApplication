package com.example.pc_100.myapplication.todolist.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ShenJack on 2017/4/11.
 */

public class TaskContentProvider extends ContentProvider {
    TaskDbHelper mTaskDbHelper;
    private static final int TASKS = 100;
    private static final int TASK_WITH_ID = 101;

    private UriMatcher uriMatcher = buildUriMatcher();

    UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(TaskContract.AUTHORITY,TaskContract.PATH_TASKS,TASKS);
        uriMatcher.addURI(TaskContract.AUTHORITY,TaskContract.PATH_TASKS + "/#",TASK_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mTaskDbHelper = new TaskDbHelper(context);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mTaskDbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        Cursor returnCursor;
        switch (match){
            case TASKS:{
                returnCursor = db.query(TaskContract.TaskEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            }
            default:
                throw new UnsupportedOperationException("Unknown uri: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);

        return returnCursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mTaskDbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match){
            case TASKS:
                long id = db.insert(TaskContract.TaskEntry.TABLE_NAME,null,values);
                if(id>0){
                    returnUri = ContentUris.withAppendedId(TaskContract.TaskEntry.CONTENT_URI,id);
                }
                else throw new SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db =mTaskDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int tasksDeleted;

        switch (match){
            case TASK_WITH_ID:
                String id = uri.getPathSegments().get(1);
                tasksDeleted = db.delete(TaskContract.TaskEntry.TABLE_NAME,"_id=?",new String[]{id});
                break;
            default:throw new UnsupportedOperationException("Unknown uri:" + uri);
        }

    if(tasksDeleted>0) {
        getContext().getContentResolver().notifyChange(uri, null);
    }
        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
