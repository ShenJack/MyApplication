package com.example.pc_100.myapplication.todolist;

import android.app.Dialog;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pc_100.myapplication.R;
import com.example.pc_100.myapplication.todolist.Data.TaskContract;

/**
 * Created by ShenJack on 2017/4/10.
 */

class NewTaskDialog extends AlertDialog {

    private EditText editTextDescription;
    private Button submitButton;
    private Button discardButton;
    private RatingBar ratingBar;

    NewTaskDialog(@NonNull final Context context) {
        super(context);
        View view = getLayoutInflater().from(context).inflate(R.layout.dialog_create_task, null);
        setContentView(view);
        editTextDescription = (EditText) findViewById(R.id.edittext_description);
        submitButton = (Button) findViewById(R.id.button_submit);
        discardButton = (Button) findViewById(R.id.button_discard);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        setTitle("Add a task");
        setView(view);
        setCancelable(true);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editTextDescription.getText().toString();
                if (description.length() == 0) {
                    return;
                }
                int priority = (int) ratingBar.getRating();
                ContentValues contentValues = new ContentValues();
                contentValues.put(TaskContract.TaskEntry.COLOMN_DESCRIPTION,description);
                contentValues.put(TaskContract.TaskEntry.COLOMN_PRIORITY,priority);
                Uri uri = context.getApplicationContext().getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI,contentValues);

                if(uri!=null){
                    Log.e("insert",contentValues.toString());
                    Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show();
                }
                Message msg = new Message();
                TodoActivity todoActivity = new TodoActivity();
                todoActivity.restartLoader();
                cancel();

            }
        });
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }


}
