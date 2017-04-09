package com.example.pc_100.myapplication.data;

import android.graphics.Color;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ShenJack on 2017/4/9.
 */

public class MainContract {
    public static final String CONTENT_AUTHORITY = "com.example.pc_100.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MAIN = "main";

    public static final class MainEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MAIN)
                .build();

        public static final String TABLE_NAME = "main";
        public static final String COLUMN_MODULE_NAME= "moduleName";
//        public static final String COLUMN_COLOR = "moduleColor";
        public static final String COLUMN_MODULE_DESCRIPTION = "moduleDescription";
    }
}
