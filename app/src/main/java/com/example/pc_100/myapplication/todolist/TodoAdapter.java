package com.example.pc_100.myapplication.todolist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc_100.myapplication.R;
import com.example.pc_100.myapplication.todolist.Data.TaskContract;


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

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.tasks_item_layout, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(TaskContract.TaskEntry._ID);
        int descriptionIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.COLOMN_DESCRIPTION);
        int priorityIndex = mCursor.getColumnIndex(TaskContract.TaskEntry.COLOMN_PRIORITY);


        mCursor.moveToPosition(position);
        final int id = mCursor.getInt(idIndex);
        int backgroundColor = getBackgroundColorByPriority(mCursor.getInt(priorityIndex));


        holder.itemView.setTag(id);
        holder.textView_description.setText(mCursor.getString(descriptionIndex));
        holder.constraintLayout.setBackgroundColor(getBackgroundColorByPriority(backgroundColor));



    }

    private int getBackgroundColorByPriority(int priority) {
        switch (priority) {
            case 1:
                return mContext.getResources().getColor(R.color.priority1);
            case 2:
                return mContext.getResources().getColor(R.color.priority2);
            case 3:
                return mContext.getResources().getColor(R.color.priority3);
            default:
                return mContext.getResources().getColor(R.color.priority3);
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textView_description;
        ConstraintLayout constraintLayout;

        public TaskViewHolder(View itemView) {
            super(itemView);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.background);
            textView_description = (TextView) itemView.findViewById(R.id.task_description);
        }
    }

    Cursor swapCursor(Cursor cursor) {
        if (mCursor == null) {
            return null;
        }
        Cursor temp = mCursor;
        mCursor = cursor;

        if (cursor != null) {
            notifyDataSetChanged();
        }
        return temp;
    }

}
