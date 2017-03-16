package com.example.pc_100.myapplication;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button bt_start_sender_act;
    public static Observer<String> mObserver;
    private TextView tv_reactor;

    private String tag = "ReactiveX";
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

        Observer<String > mObserver = new Observer<String>() {

            @Override
            public void onNext(String s) {
                tv_reactor.append(s);
            }

            @Override
            public void onCompleted() {
                Log.d("completed","completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("error","error");
            }
        };

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(tag,s);
            }
        };

        Action1<Integer> action1 = new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("test","number:"+integer);
            }
        };
        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

        Observable.create(new Observable.OnSubscribe<String >() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(" ");
            }
        });

    }

        public void onClickObservable(View v) {
            Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext("create 1");
                    subscriber.onCompleted();
                }

            });
            mObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(MainActivity.mObserver);
        }


}
