package com.example.pc_100.myapplication.todolist;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pc_100.myapplication.R;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView mTodolistRecyclerview;
    private FloatingActionButton floatingActionButton;
    private TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        mTodolistRecyclerview = (RecyclerView) findViewById(R.id.todolist_recyclerview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        mTodolistRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter(this);
        mTodolistRecyclerview.setAdapter(todoAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
