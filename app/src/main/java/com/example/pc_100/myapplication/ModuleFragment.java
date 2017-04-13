package com.example.pc_100.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc_100.myapplication.R;
import com.example.pc_100.myapplication.todolist.TodoActivity;

/**
 * Created by ShenJack on 2017/4/13.
 */

public class ModuleFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_main, container, false);
        view.findViewById(R.id.root_layout).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TodoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
