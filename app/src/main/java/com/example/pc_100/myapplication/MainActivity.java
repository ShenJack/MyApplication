package com.example.pc_100.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc_100.myapplication.data.MainContract;
import com.example.pc_100.myapplication.todolist.BlankFragment;
import com.example.pc_100.myapplication.todolist.TodoActivity;
import com.example.pc_100.myapplication.todolist.TodoFragment;
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

    private String tag = "ReactiveX";
    private BottomBar bottomBar;
    private boolean initialized = false;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                if(tabId == R.id.tab_todo){
                    BlankFragment fragment;
                    fragment =new BlankFragment();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();
                    // TODO: 2017/4/13 Use fragment instead
                }
                if(tabId == R.id.tab_modules){
                    MainFragment mainFragment = new MainFragment();
                    fragmentTransaction.replace(R.id.content,mainFragment);
                    fragmentTransaction.commit();
                }
            }
        });



    }



//    private long initializeData(){
//        ContentValues cv[] = new ContentValues()[];
//    }
}
