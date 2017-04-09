package com.example.pc_100.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rx.schedulers.NewThreadScheduler;

/**
 * Created by ShenJack on 2017/4/9.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {
    private Context mContext;
    private Cursor mCursor;


    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId;

        // TODO: 2017/4/9 Make a layout
        return null;
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder holder, int position) {

    }

    public MainAdapter(@NonNull Context context ) {
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MainAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView backgroundView;

        TextView nameView;
        TextView descriptionView;


        public MainAdapterViewHolder(View View) {
            super(View);


        }

        @Override
        public void onClick(View v) {

        }
    }

    void swapCursor(Cursor newCursor){
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
