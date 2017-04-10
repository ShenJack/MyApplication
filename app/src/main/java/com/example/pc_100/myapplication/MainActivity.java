package com.example.pc_100.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc_100.myapplication.data.MainContract;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProgressBar mLoadingIndicator;
    private String tag = "ReactiveX";
    private MainAdapter mMainAdapter;
    private SQLiteDatabase mDb;
    private BottomBar bottomBar;
    private boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                if(tabId == R.id.tab_todo){
//
//                }
            }
        });
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mMainAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mMainAdapter);

    }

    private long addNewModule(String name, String descripition) {
        ContentValues cv = new ContentValues();
        cv.put(MainContract.MainEntry.COLUMN_MODULE_NAME, name);
        cv.put(MainContract.MainEntry.COLUMN_MODULE_DESCRIPTION, descripition);
        return mDb.insert(MainContract.MainEntry.TABLE_NAME, null, cv);
    }

//    private long initializeData(){
//        ContentValues cv[] = new ContentValues()[];
//    }
}
