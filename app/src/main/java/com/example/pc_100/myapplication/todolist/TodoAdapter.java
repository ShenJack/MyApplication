package com.example.pc_100.myapplication.todolist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ShenJack on 2017/4/10.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TaskViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public TodoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(mCursor == null){
            return 0;
        }
        return mCursor.getCount();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        public TaskViewHolder(View itemView) {
            super(itemView);
        }
    }
    Cursor swapCursor(Cursor cursor){
        if(mCursor ==null){
            return null;
        }
        Cursor temp = mCursor;
        mCursor = cursor;

        if(cursor!=null){
            notifyDataSetChanged();
        }
        return temp;
    }

}
