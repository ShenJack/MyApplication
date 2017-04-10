package com.example.pc_100.myapplication.todolist.Data;

import android.net.Uri;
import android.net.rtp.AudioStream;
import android.provider.BaseColumns;

/**
 * Created by sjjkk on 2017/4/10.
 */

public class TaskContract {
    public static final String AUTHORITY = "com.example.cp_100.myapplication.todolist";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ AUTHORITY);

    public static final String PATH_TASKS = "tasks";
    public static final class TaskEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();
        public static final String TABLE_NAME = "tasks";
        public static final String COLOMN_DESCRIPTION = "description";
        public static final String COLOMN_PRIORITY = "priority";
    }
}
