package com.example.pc_100.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private Button bt_start_sender_act;
    public static Observer<String> mObserver;
    private TextView tv_reactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_reactor = (TextView) findViewById(R.id.tv_reactor);
        bt_start_sender_act = (Button) findViewById(R.id.bt_start_sender_act);
        bt_start_sender_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sender.class));
            }
        });

        Observer mObserver = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.d("completed","completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("error","error");
            }

            @Override
            public void onNext(String s) {
                tv_reactor.append(s);
            }
        };
    }

        public void onClickObservable(View v) {
//        Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("create 1");
//                subscriber.onNext("create 2");
//                subscriber.onCompleted();
//            }
//
//        });
//        mObservable.subscribe(MainActivity.mObserver);
            Observable observable = Observable.just("hello","world");
            observable.subscribe(mObserver);
    }
}
