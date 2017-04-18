package com.example.pc_100.myapplication.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pc_100.myapplication.R;
import com.example.pc_100.myapplication.todolist.Data.TaskContract;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by ShenJack on 2017/4/18.
 */

public class TodoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView mTodolistRecyclerview;
    private FloatingActionButton floatingActionButton;

    private CustomCursorAdapter mAdapter;
    private Context mContext;

    public TodoFragment() {
        super();
    }



    private static final int LOADER_ID =746;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo,null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mTodolistRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));


        mAdapter = new CustomCursorAdapter(mContext);

        mTodolistRecyclerview.setAdapter(mAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTaskDialog newTaskDialog = new NewTaskDialog(mContext);
                newTaskDialog.show();
                newTaskDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        restartLoader();
                    }
                });
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int) viewHolder.itemView.getTag();

                String stringId = Integer.toString(id);
                Uri uri = TaskContract.TaskEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();

                getActivity().getContentResolver().delete(uri,null,null);

                getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, TodoFragment.this);
            }
        }).attachToRecyclerView(mTodolistRecyclerview);

        getActivity().getSupportLoaderManager().initLoader(LOADER_ID,null,this);
        Toast.makeText(getActivity(), "start", LENGTH_SHORT).show();
    }



    @Override
    public void onResume() {
        Log.d("resume","resume");
        super.onResume();
        getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, this);

    }

    void restartLoader(){
        getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(getActivity()) {
            Cursor mTaskData = null;

            @Override
            protected void onStartLoading() {
                if(mTaskData!=null){
                    deliverResult(mTaskData);
                }
                else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getActivity().getContentResolver().query(TaskContract.TaskEntry.CONTENT_URI
                            ,null
                            ,null
                            ,null
                            , TaskContract.TaskEntry.COLOMN_PRIORITY);
                }catch (Exception e){
                    Log.e(" ", "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
