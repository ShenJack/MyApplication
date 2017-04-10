package com.example.pc_100.myapplication.todolist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pc_100.myapplication.R;

/**
 * Created by ShenJack on 2017/4/10.
 */

class NewTaskDialog extends Dialog{

    public NewTaskDialog(@NonNull Context context) {
        super(context);
        View view = getLayoutInflater().from(context).inflate(R.layout.dialog_create_task,null);
        setContentView(view);

    }




}
